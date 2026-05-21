package se.su.inlupp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Optional;

public class Gui extends Application {

    private FileChooser fileChooser = new FileChooser();
    private Stage stage;

  public void start(Stage stage) {
      stage = stage;
      stage.setTitle("BERRYS AND SHROOOOMS");
      Graph<String> graph = new ListGraph<String>();


      BorderPane root = new BorderPane();
      root.setStyle("-fx-font-weight: bold");

      HBox topBar = new HBox(5);
      HBox bottomBar = new HBox(5);


      MenuBar menuBar = new MenuBar();

      Menu start = new Menu("Start");
      menuBar.getMenus().add(start);
      //MenuItem createNew = new MenuItem("New.. ");
      MenuItem open = new MenuItem("Open");

      open.setOnAction(new OpenHandler());
      MenuItem save = new MenuItem("Save");

      save.setOnAction(new SaveHandler());
      MenuItem exit = new MenuItem("Exit");
      start.getItems().addAll(open,save,exit);


      VBox topV = new VBox(menuBar, topBar);


      Button newLocation = new Button("Add new location");
      Button openImage = new Button("Add image");
      //Button saveButton = new Button("Save");
      Button exitButton = new Button("Exit");
      TextField textField = new TextField();
      Button enterButton = new Button("Enter");
      Label resultLabel = new Label();
      //resultLabel.prefWidth(100);
      root.setBottom(resultLabel);




      topBar.getChildren().addAll(
              //topV,
              //saveButton,
              newLocation,
              openImage,
              exitButton
              //textField,
              //enterButton

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
        newLocation.setOnAction(event -> {topBar.getChildren().addAll(textField, enterButton);});



      topBar.setAlignment(Pos.CENTER);
      bottomBar.setAlignment(Pos.CENTER);
      root.setTop(topV);
      root.setBottom(bottomBar);

      Pane canvas = new Pane();
      root.setCenter(canvas);

      canvas.setOnMouseClicked((event) -> {
          canvas.getChildren().add(new LocationNodeGui(100,100));
      });

      Scene scene = new Scene(root, 640, 480);
      stage.setScene(scene);
      stage.show();
  }
  private class OpenHandler implements EventHandler<ActionEvent>{

      @Override
      public void handle(ActionEvent event) {
          fileChooser.setInitialDirectory(new File("."));
          File openFile = fileChooser.showOpenDialog(stage);
          System.out.println(openFile);
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
  private class exitHandler implements EventHandler<WindowEvent>{

        @Override
        public void handle(WindowEvent event) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Are you sure you wanna close?!");

            Optional<ButtonType> clicked = alert.showAndWait();
            if(clicked.isPresent() && clicked.get().equals(ButtonType.CANCEL)){
                event.consume();
            }

        }
    }


  public static void main(String[] args) {
    launch(args);
  }
}
