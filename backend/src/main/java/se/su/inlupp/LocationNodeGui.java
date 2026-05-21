package se.su.inlupp;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;

public class LocationNodeGui extends Pane {
    double startX, startY;

    public LocationNodeGui(double x, double y) {
        relocate(x, y);
        Circle circle = new Circle(20, 20, 20);
        circle.setFill(Color.GREEN);
        getChildren().add(circle);
        setPrefSize(40, 40);

        setOnMousePressed((event) -> {
            startX = event.getX();
            startY = event.getY();
        });

        setOnMouseDragged((event) -> {
            double newX = getLayoutX() + event.getX() - startX;
            double newY = getLayoutY() + event.getY() - startY;
            relocate(newX, newY);
        });



    }
}
