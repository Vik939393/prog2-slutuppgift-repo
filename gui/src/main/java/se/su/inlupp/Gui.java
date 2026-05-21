package se.su.inlupp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Gui extends Application {

  public void start(Stage stage) {
      stage.setTitle("BERRYS AND SHROOOOMS");
      Graph<String> graph = new ListGraph<String>();
      String javaVersion = System.getProperty("java.version");
      String javafxVersion = System.getProperty("javafx.version");



      BorderPane root = new BorderPane();
      root.setStyle("-fx-font-weight: bold");

      HBox topBar = new HBox(5);
      HBox bottomBar = new HBox(5);

      Button newButton = new Button("New Node");
      Button openButton = new Button("Open");
      Button saveButton = new Button("Save");
      Button exitButton = new Button("Exit");
      TextField textField = new TextField();
      Button searchButton = new Button("Search");
      Label resultLabel = new Label();
      //resultLabel.prefWidth(100);
      root.setBottom(resultLabel);


      topBar.getChildren().addAll(
              saveButton,
              newButton,
              openButton,
              exitButton,
              textField,
              searchButton

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
        searchButton.setOnAction(event -> {resultLabel.setText("Hej " + textField.getText());});



      topBar.setAlignment(Pos.CENTER);
      bottomBar.setAlignment(Pos.CENTER);
      root.setTop(topBar);
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

  public static void main(String[] args) {
    launch(args);
  }
}
