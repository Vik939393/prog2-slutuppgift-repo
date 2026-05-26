package se.su.inlupp;

import javafx.scene.control.Alert;

public class AlertHelper {

    public static void showError(String exception) {
        Alert alert  = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(exception);
        alert.showAndWait();
    }
}
