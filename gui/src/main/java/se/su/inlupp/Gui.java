package se.su.inlupp;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Gui extends Application {

  public void start(Stage stage) {
      stage.setTitle("BERRYS AND SHROOOOMS");
      Graph<String> graph = new ListGraph<String>();
      String javaVersion = System.getProperty("java.version");
      String javafxVersion = System.getProperty("javafx.version");
      Label label =
              new Label("Hello, ADRIAN " + javafxVersion + ", running on CHILLMODE " + javaVersion + ".");

      BorderPane root = new BorderPane();
      root.setStyle("-fx-font-weight: bold");

      Label top = new Label("BERRYS AND SHROOOOOMS");
      //top.setAlignment(Pos.TOP_RIGHT);
      top.setPrefWidth(200);
      BorderPane.setAlignment(top, Pos.TOP_CENTER);
      root.setTop(top);
      Scene scene = new Scene(root, 640, 480);
      stage.setScene(scene);
      stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
