package sample.Controllers;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class PaymentConfirmed extends LanguageController {

    @FXML private Label paymentSucceded;
    @FXML private Button cancelButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadLangTexts(getLangBundle());
    }
    public void closeMe(ActionEvent actionEvent) {
        Node  source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @Override
    public void loadLangTexts(ResourceBundle langBundle) {
        paymentSucceded.setText(langBundle.getString("payment_succeded"));
    }
}
