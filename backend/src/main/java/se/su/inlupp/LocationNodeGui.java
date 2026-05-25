package se.su.inlupp;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;

import java.util.Optional;

public class LocationNodeGui extends Pane {
    double startX, startY;
    String name;

    public LocationNodeGui(double x, double y, String name, String berryAmount) {
        this.name = name;
        relocate(x, y);
        Circle circle = new Circle(20, 20, 20);
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

        delete.setOnAction(event -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Delete this location?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    Pane parent = (Pane) getParent(); //För att kunna använda getChildren() måste den förstå att den är en Pane
                    parent.getChildren().remove(this);
                    event.consume();
                }

        });
    }

    public void setColor(LocationType type, Circle c) {
        switch (type) {
            case SMALL_AMOUNT_BERRIES -> c.setFill(Color.INDIANRED);
            case MEDIUM_AMOUNT_BERRIES -> c.setFill(Color.MEDIUMVIOLETRED);
            case BIG_AMOUNT_BERRIES -> c.setFill(Color.DARKRED);
        }

    }
}
