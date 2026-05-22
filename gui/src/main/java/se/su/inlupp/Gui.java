package se.su.inlupp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    private FileChooser fileChooser = new FileChooser();
    private Stage stage;
    private Pane canvas;
    private Scene scene;
    private Graph<String> graph;

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
      /*searchButton.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
            resultLabel.setText("Hej " + textField.getText());
          }
      });*/

      Pane centerCanvas = new Pane();
      root.setCenter(centerCanvas);

        newLocation.setOnAction(event -> {
            TextInputDialog createNode = new TextInputDialog();
            createNode.setTitle("New location");
            createNode.setHeaderText("Enter location name:");
            Optional<String> result = createNode.showAndWait();
            if (result.isPresent()) {
                String name = result.get();
                centerCanvas.setOnMouseClicked((event2) -> {
                    if (event2.getTarget() == centerCanvas) {
                        centerCanvas.getChildren().add(new LocationNodeGui(event2.getX(),event2.getY(), name));
                        centerCanvas.setOnMouseClicked(null);
                    }

                });
            }
        });




      topBar.setAlignment(Pos.CENTER);
      bottomBar.setAlignment(Pos.CENTER);
      root.setTop(topV);
      root.setBottom(bottomBar);
      canvas = new Pane();

      root.setCenter(canvas);

      canvas.setOnMouseClicked((event) -> {
          double x = event.getX();
          double y = event.getY();
          canvas.getChildren().add(new LocationNodeGui(x,y, name));
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

              canvas.getChildren().addAll(backgroundView);
          }
          else{
              if(confirm()) {
                  fileChooser.setInitialDirectory(new File("."));
                  File openFile = fileChooser.showOpenDialog(stage);

                  Image background = new Image(openFile.toURI().toString());
                  ImageView backgroundView = new ImageView(background);

                  backgroundView.fitHeightProperty().bind(canvas.heightProperty());
                  backgroundView.fitWidthProperty().bind(canvas.widthProperty());

                  canvas.getChildren().addAll(backgroundView);
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
