package sample.Controllers;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class RoomDetailsController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void getRoomToShow(Rooms room,long daysToStay){
        id.setText(String.valueOf(room.getRoom_number()));
        floor.setText(String.valueOf(room.getFloor_number()));
        beds.setText(String.valueOf(room.getBed_number()));
        toPay.setText(String.valueOf(room.getPrice()*daysToStay));
        roomType.setText(capitalize(room.getRoom_type()));
    }
    
    public void setRemoveButtonAction(EventHandler<ActionEvent> eventHandler){
        removeButton.setOnAction(eventHandler);
    }
    
    private String capitalize(String word){
        String cap = word.substring(0, 1).toUpperCase() + word.substring(1);
        return cap;
    }
}
