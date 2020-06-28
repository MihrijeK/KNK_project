package sample.Controllers;

import DatabaseConnection.dbConnection;
import Helpers.Rooms;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class EditRoomsView {
    private Connection connection = dbConnection.getConnection();
    private final Stage stage;
    private Rooms rooms;
    @FXML private TextField roomNumber;
    @FXML private TextField floorNumber;
    @FXML private TextField roomCapacity;
    @FXML private TextField bedNumber;
    @FXML private TextField roomType;
    @FXML private TextField Price;
    @FXML private Button updateRoom;
    @FXML private Button cancleButton;

    public EditRoomsView(Rooms rooms) throws Exception {
        this.rooms = rooms;
        stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/EditRoomsView.fxml"));
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
    public void display() throws IOException, SQLException {
        roomNumber.setText(Integer.toString(rooms.getRoom_number()));
        floorNumber.setText(Integer.toString(rooms.getFloor_number()));
        roomCapacity.setText(Integer.toString(rooms.getCapacity()));
        bedNumber.setText(Integer.toString(rooms.getBed_number()));
        roomType.setText(rooms.getRoom_type());
        Price.setText(Double.toString(rooms.getPrice()));

        updateRoom.setOnAction(e->{
            try {
                updateRoom(Integer.parseInt(roomNumber.getText()),Integer.parseInt(floorNumber.getText()),Integer.parseInt(roomCapacity.getText()),Integer.parseInt(bedNumber.getText()),roomType.getText(),
                        Double.parseDouble(Price.getText()));
                System.out.println("Updated");
                stage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        cancleButton.setOnAction(e->stage.close());
        stage.showAndWait();
    }
    public void updateRoom(int room_number,int floor_number,int capacity,int bed_number,String room_type,double price) throws Exception {
        String query = "UPDATE rooms SET room_number = "+room_number+" ,floor_number = "+floor_number+",capacity = "+capacity+",bed_number = "+bed_number+",room_type = '"+room_type+"'," +
                "price = "+price+" WHERE room_number = "+ room_number+";";

        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
    }
}