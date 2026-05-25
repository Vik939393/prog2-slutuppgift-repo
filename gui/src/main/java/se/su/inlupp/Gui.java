package se.su.inlupp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.*;
import java.util.Optional;

public class Gui extends Application {

    private final Controller controller = new Controller();
    private FileChooser fileChooser = new FileChooser();
    private Stage stage;
    private Pane canvas;
    private Scene scene;
    private String locationName;
    private Graph<String> graph;
    private boolean locationAdded;

  public void start(Stage stage) {
      this.stage = stage;
      stage.setTitle("BERRYS AND SHROOOOMS");
      graph = new ListGraph<String>();


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

      start.getItems().addAll(createNew, open,save,exit);


      VBox topV = new VBox(menuBar, topBar);


      Button newLocation = new Button("Add new location");

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
                      canvas.getChildren().add(new LocationNodeGui(x, y, locationName, berryAmount));
                      newEvent.consume();
                      locationAdded = false;

                  }

              });
          }

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
              graph = new ListGraph<>();
          }
      }
  }
  private class OpenBackgroundHandler implements EventHandler<ActionEvent>{

      @Override
      public void handle(ActionEvent event) {
          if(canvas.getChildren().isEmpty()) {
              fileChooser.setInitialDirectory(new File("."));
              File openFile = fileChooser.showOpenDialog(stage);

              Image background = new Image(openFile.toURI().toString());
              ImageView backgroundView = new ImageView(background);

              backgroundView.fitHeightProperty().bind(canvas.heightProperty());
              backgroundView.fitWidthProperty().bind(canvas.widthProperty());

              canvas.getChildren().add(0, backgroundView);
              backgroundView.setMouseTransparent(true);
              backgroundView.toBack();
          }
          else{
              if(confirm()) {
                  fileChooser.setInitialDirectory(new File("."));
                  File openFile = fileChooser.showOpenDialog(stage);

                  Image background = new Image(openFile.toURI().toString());
                  ImageView backgroundView = new ImageView(background);

                  backgroundView.fitHeightProperty().bind(canvas.heightProperty());
                  backgroundView.fitWidthProperty().bind(canvas.widthProperty());

                  canvas.getChildren().add(0, backgroundView);
                  backgroundView.setMouseTransparent(true);
                  backgroundView.toBack();
              }
          }


      }
  }

  private class SaveHandler implements EventHandler<ActionEvent>{

      @Override
      public void handle(ActionEvent event) {
          File saveFile = fileChooser.showSaveDialog(stage);
          try {
              FileOutputStream fos =new FileOutputStream(saveFile);
              ObjectOutputStream oos = new ObjectOutputStream(fos);
          }catch(IOException e){
              e.printStackTrace();
          }
          System.out.println(saveFile);

      }
  }
  private class OpenHandler implements EventHandler<ActionEvent>{

      @Override
      public void handle(ActionEvent event) {
          fileChooser.setInitialDirectory(new File("."));
          File openFile = fileChooser.showOpenDialog(stage);
          try{
              FileReader fileReader = new FileReader((openFile));
              BufferedReader reader = new BufferedReader(fileReader);
              String line;
              while(((line = reader.readLine()) != null)){
                  System.out.println(line);
              }

              reader.close();
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
    private boolean confirm(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("You have unsaved changes, do you want to continue?");

        Optional<ButtonType> clicked = alert.showAndWait();
        return clicked.isPresent() && clicked.get().equals(ButtonType.OK);
    }


  public static void main(String[] args) {
    launch(args);
  }
}
