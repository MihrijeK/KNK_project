package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {


    @FXML private ImageView building;
    @FXML private Label sunhotel;
    @FXML private Button login_button;
    
        public void changeScreen(ActionEvent event) throws IOException {
       Parent loginParent= FXMLLoader.load(getClass().getResource("login.fxml"));
       Scene loginscene=new Scene(loginParent);

        @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
