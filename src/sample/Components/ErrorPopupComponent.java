package sample.Components;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class ErrorPopupComponent {
    public static void show(Exception e) {
        e.printStackTrace();
        String msg = e.getMessage();
        show(msg != null && msg.length() > 0 ? msg : e.toString());
    }

    public static void show(String message) {
        show(message, "Error");
    }

    public static void show(String message, String title) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Connection - Error");
        Label label = new Label(message);
        label.setPrefWidth(300);
        label.setMaxWidth(300);
        label.setWrapText(true);
        alert.setHeaderText(title);
        alert.getDialogPane().setContent(new StackPane(label));
        alert.showAndWait();
    }
}