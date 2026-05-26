package se.su.inlupp;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;

import java.util.Optional;

public class LocationNodeGui extends Pane {
    double startX, startY;
    String name;
    Circle circle;
    private String berryAmount;

    public LocationNodeGui(double x, double y, String name, String berryAmount) {
        this.name = name;
        this.berryAmount = berryAmount;
        relocate(x, y);
        circle = new Circle(20, 20, 20);
        LocationType amount = LocationType.fromString(berryAmount);
        setColor(amount, circle);
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

        ContextMenu menu = new ContextMenu();
        MenuItem delete = new MenuItem("Delete");

        menu.getItems().add(delete);

        setOnContextMenuRequested(event -> menu.show(this, event.getScreenX(), event.getScreenY())
        );




    }

    public String getName() {
        return name;
    }

    public Circle getCircle() {
        return circle;
    }

    public String getBerryAmount() {
        return berryAmount;
    }

    public void setColor(LocationType type, Circle c) {
        switch (type) {
            case SMALL_AMOUNT_BERRIES -> c.setFill(Color.INDIANRED);
            case MEDIUM_AMOUNT_BERRIES -> c.setFill(Color.MEDIUMVIOLETRED);
            case BIG_AMOUNT_BERRIES -> c.setFill(Color.DARKRED);
        }

    }

}
