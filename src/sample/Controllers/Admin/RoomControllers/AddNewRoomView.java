package sample.Controllers.Admin.RoomControllers;

import Helpers.Rooms;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Controllers.AdminDashboard;
import sample.Controllers.LanguageController;
import sample.Repositories.RoomRespository;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class AddNewRoomView extends LanguageController {
    private final Stage stage;

    @FXML private TextField roomNumber;
    @FXML private TextField floorNumber;
    @FXML private TextField roomCapacity;
    @FXML private TextField bedNumber;
    @FXML private ChoiceBox roomTypeBox;
    @FXML private TextField Price;
    @FXML private Button addNewRoom;
    @FXML private Button cancleButton;

    @FXML private Label lbl_roomNr;
    @FXML private Label lbl_floorNr;
    @FXML private Label lbl_roomCap;
    @FXML private Label lbl_bedNr;
    @FXML private Label lbl_roomType;
    @FXML private Label lbl_price;

    private ArrayList<String> roomTypeList = new ArrayList<>(Arrays.asList(new String[]{"Single","Double","Triple","Quad","Double-double","Master Suite","Junior Suite"}));

    public AddNewRoomView(){
        stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(AdminDashboard.class.getResource("../Views/AdminViews/RoomsViews/AddNewRoomView.fxml"));
            loader.setController(this);

            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Edit staff member");
            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void display(ObservableList<Rooms> room){
        addNewRoom.setOnAction(e -> {
            try {
                createButton(createRoomsObj(roomNumber, floorNumber, roomCapacity, bedNumber, roomTypeBox, Price),room);
                System.out.println("DONE");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        cancleButton.setOnAction(e -> stage.close());
        for (String r : roomTypeList) {
            roomTypeBox.getItems().addAll(r);
        }
        stage.showAndWait();
    }

    public Rooms createRoomsObj(TextField roomNumber, TextField floorNumber, TextField roomCapacity, TextField bedNumber, ChoiceBox roomType, TextField Price) {
        int room_number = Integer.parseInt(roomNumber.getText());
        int floor_number = Integer.parseInt(floorNumber.getText());
        int capacity = Integer.parseInt(roomCapacity.getText());
        int bed_number = Integer.parseInt(bedNumber.getText());
        double price = Double.parseDouble(Price.getText());
        String rType = roomType.getValue().toString();

        Rooms rms = new Rooms(room_number, floor_number, capacity, bed_number, rType, price);
        return rms;
    }

    public void createButton(Rooms rooms,ObservableList<Rooms> room) throws Exception {
        RoomRespository.insert(rooms);
        room.addAll(rooms);
        System.out.println("Done");

        roomNumber.clear();
        floorNumber.clear();
        roomCapacity.clear();
        bedNumber.clear();
        Price.clear();
    }

    @Override
    public void loadLangTexts(ResourceBundle langBundle) {
        lbl_roomNr.setText(langBundle.getString("roomNr"));
        lbl_floorNr.setText(langBundle.getString("floorNr"));
        lbl_roomCap.setText(langBundle.getString("roomCapacity"));
        lbl_bedNr.setText(langBundle.getString("bedNumber"));
        lbl_roomType.setText(langBundle.getString("roomType"));
        lbl_price.setText(langBundle.getString("price"));
        addNewRoom.setText(langBundle.getString("Add"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadLangTexts(getLangBundle());
    }
}
