package se.su.inlupp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class Gui extends Application {

    private final Controller controller = new Controller();
    private FileChooser fileChooser = new FileChooser();
    private Stage stage;
    private Pane canvas;
    private Scene scene;
    private String locationName;
    //private Graph<String> graph;
    private boolean locationAdded;
    private String currentImagePath;
    private boolean unsavedChanges;
    private LocationNodeGui from;
    private LocationNodeGui to;
    private static int edgeCounter = 1;
    private List<LocationNodeGui> locationNodes = new ArrayList<>();


  public void start(Stage stage) {
      this.stage = stage;
      stage.setTitle("BERRYS AND SHROOOOMS");
      //graph = new ListGraph<String>();


      BorderPane root = new BorderPane();
      root.setStyle("-fx-font-weight: bold");

      HBox topBar = new HBox(5);
      HBox bottomBar = new HBox(5);


      MenuBar menuBar = new MenuBar();

      Menu start = new Menu("Start");
      menuBar.getMenus().add(start);

      MenuItem createNew = new MenuItem("New");
      createNew.setOnAction(new NewHandler());


      MenuItem open = new MenuItem("Open");

      open.setOnAction(new OpenHandler());

      MenuItem save = new MenuItem("Save");

      save.setOnAction(new SaveHandler());

      MenuItem exit = new MenuItem("Exit");
      exit.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
              if(confirm()) {
                  stage.close();
              }
          }
      });

      start.getItems().addAll(createNew,open,save,exit);


      VBox topV = new VBox(menuBar, topBar);


      Button newLocation = new Button("Add new location");

      Button connectLocations = new Button("Connect");

      Button BFS = new Button("Find shortest path (BFS)");

      Button DFS = new Button("Find existing path (DFS");

      Button background = new Button("Add new background");
      background.setOnAction(new OpenBackgroundHandler());


      TextField textField = new TextField();
      Button enterButton = new Button("Enter");
      Label resultLabel = new Label();
      //resultLabel.prefWidth(100);
      root.setBottom(resultLabel);




      topBar.getChildren().addAll(
              newLocation,
              connectLocations,
              BFS,
              DFS,
              background

      );
      bottomBar.getChildren().addAll(
              resultLabel

      );

      Pane centerCanvas = new Pane();
      root.setCenter(centerCanvas);




      topBar.setAlignment(Pos.CENTER);
      bottomBar.setAlignment(Pos.CENTER);
      root.setTop(topV);
      root.setBottom(bottomBar);
      canvas = new Pane();

      root.setCenter(canvas);


      newLocation.setOnAction(event -> {
          locationAdded = true;
          Dialog<ButtonType> createNode = new Dialog();
          createNode.setTitle("New location");
          TextField nameField = new TextField();
          nameField.setPromptText("Name of new location:");

          ChoiceBox<String> chooseBerryAmount = new ChoiceBox<>();
          chooseBerryAmount.getItems().addAll("Small amount of berries",
                  "Medium amount of berries", "Large amount of berries");

          HBox createWindow = new HBox(10);
          createWindow.getChildren().addAll(
                  new Label("Name:"), nameField,
                  new Label("Amount of berries:"), chooseBerryAmount
          );

          createNode.getDialogPane().setContent(createWindow);
          createNode.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

          Optional<ButtonType> result = createNode.showAndWait();
          if(result.isPresent() && result.get() == ButtonType.OK){

              String name = nameField.getText();
              String berryAmount = chooseBerryAmount.getValue();

              canvas.setOnMouseClicked((newEvent) -> {

                  if (locationAdded && newEvent.getTarget() == canvas && newEvent.getButton() == MouseButton.PRIMARY) {

                      double x = newEvent.getX();
                      double y = newEvent.getY();
                      controller.addNode(name, berryAmount);
                      LocationNodeGui node = new LocationNodeGui(x,y,name,berryAmount);
                      rightClick(node);
                      canvas.getChildren().add(node);
                      locationNodes.add(node);
                      unsavedChanges = true;

                      newEvent.consume();
                      locationAdded = false;

                  }

              });
          }


              });

      connectLocations.setOnAction(event-> {
          for (Node n : canvas.getChildren()) {
              if(n instanceof LocationNodeGui lng) {
                  lng.setOnMouseClicked(newEvent -> {
                      if (newEvent.getButton() == MouseButton.PRIMARY) {
                          if (from == null) {
                              from = lng;
                              System.out.println("From är satt till: " + lng.getName());
                          } else {
                              to = lng;
                              drawConnectionLine(from, to);
                              System.out.println("To är satt till: " + lng.getName());
                              controller.connectNodes(from.getName(), to.getName(), "Edge " + edgeCounter, 1);
                              unsavedChanges = true;
                              from = null;
                              to = null;
                              newEvent.consume();
                          }

                      }
                  });
              }
          }
          edgeCounter ++;
      });

      scene = new Scene(root, 640, 480);

      stage.setScene(scene);
      stage.setOnCloseRequest(new ExitHandler());
      stage.show();
  }
  private class NewHandler implements EventHandler<ActionEvent>{

      @Override
      public void handle(ActionEvent event) {
          if(confirm()) {
              canvas.getChildren().clear();
              controller.clear();
          }
      }
  }
  private class OpenBackgroundHandler implements EventHandler<ActionEvent>{

      @Override
      public void handle(ActionEvent event) {
          if(unsavedChanges) {
              if(confirm()) {
                  openBackground();
              }
          }
          else{
                  openBackground();
          }
      }
  }

  private class SaveHandler implements EventHandler<ActionEvent>{

      @Override
      public void handle(ActionEvent event) {
          File saveFile = fileChooser.showSaveDialog(stage);
          if(saveFile==null)
              return;
          try {
              FileWriter fw =new FileWriter(saveFile);
              BufferedWriter bw = new BufferedWriter(fw);

              bw.write("IMAGE;" + currentImagePath);
              bw.newLine();
              for(LocationNodeGui node : locationNodes){
                  bw.write("LOCATION;" + node.getName()+";"+node.getLayoutX() +";" + node.getLayoutY() +";" +
                          node.getBerryAmount());
                  bw.newLine();
              }

              bw.close();
              unsavedChanges = false;

          }catch(IOException e){
              e.printStackTrace();
          }
          System.out.println(saveFile);

      }
  }
  private class OpenHandler implements EventHandler<ActionEvent>{

      @Override
      public void handle(ActionEvent event) {
          if(!confirm()){
              return;
          }
          fileChooser.setInitialDirectory(new File("."));
          File openFile = fileChooser.showOpenDialog(stage);
          if(openFile==null)
              return;

          try{
              FileReader fileReader = new FileReader((openFile));
              BufferedReader reader = new BufferedReader(fileReader);
              canvas.getChildren().clear();
              locationNodes.clear();
              controller.clear();
              String line;
              while(((line = reader.readLine()) != null)){
                  String [] split  = line.split(";");
                     if(split[0].equals("IMAGE")){
                         currentImagePath = split[1];
                         Image background = new Image(split[1]);
                         ImageView backgroundView = new ImageView(background);

                         backgroundView.fitHeightProperty().bind(canvas.heightProperty());
                         backgroundView.fitWidthProperty().bind(canvas.widthProperty());

                         canvas.getChildren().add(0, backgroundView);
                         backgroundView.setMouseTransparent(true);

                      } else if(split[0].equals("LOCATION")){
                         String name = split[1];
                         double x = Double.parseDouble(split[2]);
                         double y = Double.parseDouble(split[3]);
                         String berryAmount = split[4];
                         LocationNodeGui node = new LocationNodeGui(x, y, name, berryAmount);
                         rightClick(node);
                         canvas.getChildren().add(node);
                         locationNodes.add(node);
                         controller.addNode(node.getName(), berryAmount);

                     }
                  }


              reader.close();
              unsavedChanges = false;
          } catch (FileNotFoundException e) {
              throw new RuntimeException(e);
          } catch (IOException e) {
              throw new RuntimeException(e);
          }


      }
  }

  private class ExitHandler implements EventHandler<WindowEvent>{

        @Override
        public void handle(WindowEvent event) {
            if(!confirm()){
                event.consume();
            }

        }
    }
    private boolean confirm() {
        if (unsavedChanges) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("You have unsaved changes, do you want to continue?");

            Optional<ButtonType> clicked = alert.showAndWait();
            return clicked.isPresent() && clicked.get().equals(ButtonType.OK);
        }
        return true;
    }
    private void openBackground(){
        fileChooser.setInitialDirectory(new File("."));
        File openFile = fileChooser.showOpenDialog(stage);
        currentImagePath = openFile.toURI().toString();

        Image background = new Image(currentImagePath);
        ImageView backgroundView = new ImageView(background);

        backgroundView.fitHeightProperty().bind(canvas.heightProperty());
        backgroundView.fitWidthProperty().bind(canvas.widthProperty());

        canvas.getChildren().removeIf(node -> node instanceof ImageView);

        canvas.getChildren().add(0, backgroundView);
        backgroundView.setMouseTransparent(true);
        unsavedChanges = true;
    }
    private void drawConnectionLine(LocationNodeGui from,LocationNodeGui to){
        Line newLine = new javafx.scene.shape.Line();
        newLine.startXProperty().bind(from.layoutXProperty().add(20));
        newLine.startYProperty().bind(from.layoutYProperty().add(20));

        newLine.endXProperty().bind(to.layoutXProperty().add(20));
        newLine.endYProperty().bind(to.layoutYProperty().add(20));

        canvas.getChildren().add(1, newLine);
    }
    private void rightClick(LocationNodeGui node){
        ContextMenu menu = new ContextMenu();
        MenuItem delete = new MenuItem("Delete");

        delete.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Delete this location?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
               controller.removeNode(node.getName());
               canvas.getChildren().remove(node);
               locationNodes.remove(node);
               unsavedChanges = true;

               event.consume();
            }

        });

        menu.getItems().add(delete);

        node.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                menu.show(node, event.getScreenX(), event.getScreenY());
                event.consume();
            }
        });
    }

  public static void main(String[] args) {
    launch(args);
  }
}
