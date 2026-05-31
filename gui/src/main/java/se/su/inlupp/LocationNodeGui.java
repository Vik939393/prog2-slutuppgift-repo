// PROG2 VT2026, Inlämningsuppgift, del 1
// Grupp 153
// Viktor Spasov visp9819
// Adrian Nötzel adno3118

package se.su.inlupp;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;

public class LocationNodeGui extends Pane {
    double startX, startY;
    private String name;
    private Circle circle;
    private String berryAmount;
    private boolean wasDragged;

    public LocationNodeGui(double x, double y, String name, String berryAmount) {
        this.name = name;
        this.berryAmount = berryAmount;
        relocate(x, y);
        circle = new Circle(20, 20, 20);
        LocationType amount = LocationType.fromString(berryAmount);
        setColor(amount, circle);
        getChildren().add(circle);
        setPrefSize(30, 30);

        setOnMousePressed((event) -> {
            startX = event.getX();
            startY = event.getY();
            event.consume();
        });

        setOnMouseDragged((event) -> {
            wasDragged = true;
            double newX = getLayoutX() + event.getX() - startX;
            double newY = getLayoutY() + event.getY() - startY;
            relocate(newX, newY);
            event.consume();
        });

        setOnMouseClicked(event -> {
            if (wasDragged) {
                wasDragged = false;
                event.consume();
            }
        });
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

    public boolean getWasDragged() {
        return wasDragged;
    }

    public void setWasDragged(boolean wasDragged) {
        this.wasDragged = wasDragged;
    }

}
