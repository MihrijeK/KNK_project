package sample.Controllers.Admin.RoomControllers;

import Helpers.Rooms;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import sample.Controllers.AdminDashboard;
import sample.Repositories.RoomRespository;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class EditRoomsView {
    private final Stage stage;
    private Rooms rooms;
    @FXML private TextField roomNumber;
    @FXML private TextField floorNumber;
    @FXML private TextField roomCapacity;
    @FXML private TextField bedNumber;
    @FXML private ChoiceBox roomTypeBox;
    @FXML private TextField Price;
    @FXML private Button updateRoom;
    @FXML private Button cancleButton;

    private ArrayList<String> roomTypeList = new ArrayList<>(Arrays.asList(new String[]{"Single","Double","Triple","Quad","Double-double","Master Suite","Junior Suite"}));

    public EditRoomsView(Rooms rooms) throws Exception {
        this.rooms = rooms;
        stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(AdminDashboard.class.getResource("../Views/AdminViews/RoomsViews/EditRoomsView.fxml"));

            loader.setController(this);

            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Edit staff member");
            for (String r : roomTypeList) {
                roomTypeBox.getItems().addAll(r);
            }
            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void display(ObservableList<Rooms> room) throws IOException, SQLException {
        roomNumber.setText(Integer.toString(rooms.getRoom_number()));
        floorNumber.setText(Integer.toString(rooms.getFloor_number()));
        roomCapacity.setText(Integer.toString(rooms.getCapacity()));
        bedNumber.setText(Integer.toString(rooms.getBed_number()));
        roomTypeBox.setValue(rooms.getRoom_type());
        Price.setText(Double.toString(rooms.getPrice()));
        int roomNum = rooms.getRoom_number();
        updateRoom.setOnAction(e -> {
            try {
                Rooms rooms = new Rooms(Integer.parseInt(roomNumber.getText()), Integer.parseInt(floorNumber.getText()), Integer.parseInt(roomCapacity.getText()), Integer.parseInt(bedNumber.getText()), roomTypeBox.getValue().toString(),
                        Double.parseDouble(Price.getText()));
                room.addAll(rooms);
                RoomRespository.update(rooms,roomNum);
                System.out.println("Updated");
                stage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        cancleButton.setOnAction(e -> stage.close());
        stage.showAndWait();
    }

}
