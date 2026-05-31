// PROG2 VT2026, Inlämningsuppgift, del 1
// Grupp 153
// Viktor Spasov visp9819
// Adrian Nötzel adno3118

package se.su.inlupp;

import javafx.scene.control.Alert;

public class AlertHelper {

    public static void showError(String exception) {
        Alert alert  = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(exception);
        alert.showAndWait();
    }


}
