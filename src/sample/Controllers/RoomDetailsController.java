package sample.Controllers;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class RoomDetailsController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    
    public void setRemoveButtonAction(EventHandler<ActionEvent> eventHandler){
        removeButton.setOnAction(eventHandler);
    }
}
