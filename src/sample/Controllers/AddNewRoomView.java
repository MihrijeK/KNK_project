package sample.Controllers;

import DatabaseConnection.dbConnection;
import Helpers.Rooms;
import Helpers.Staff;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class AddNewRoomView {
    private Connection connection = dbConnection.getConnection();
    private final Stage stage;
    @FXML private TextField roomNumber;
    @FXML private TextField floorNumber;
    @FXML private TextField roomCapacity;
    @FXML private TextField bedNumber;
    @FXML private TextField roomType;
    @FXML private TextField Price;
    @FXML private Button addNewRoom;
    @FXML private Button cancleButton;

    public AddNewRoomView() throws Exception {
        stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/AddNewRoomView.fxml"));
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
        addNewRoom.setOnAction(e-> {
            try {
                createButton(createRoomsObj(roomNumber,floorNumber,roomCapacity,bedNumber,roomType,Price));
                System.out.println("DONE");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        cancleButton.setOnAction(e->stage.close());
        stage.showAndWait();
    }

    public Rooms createRoomsObj(TextField roomNumber, TextField floorNumber, TextField roomCapacity, TextField bedNumber, TextField roomType, TextField Price){
        int room_number = Integer.parseInt(roomNumber.getText());
        int floor_number = Integer.parseInt(floorNumber.getText());
        int capacity = Integer.parseInt(roomCapacity.getText());
        int bed_number = Integer.parseInt(bedNumber.getText());
        String room_type = roomType.getText();
        double price = Double.parseDouble(Price.getText());

        Rooms rms = new Rooms(room_number,floor_number,capacity,bed_number,room_type,price);
        return rms;
    }

    public String roomsQuery(Rooms room) throws Exception {
        StringBuilder query = new StringBuilder();

        query.append("INSERT INTO rooms VALUES(");
        query.append(room.getRoom_number());
        query.append(", "+room.getFloor_number());
        query.append(", "+room.getCapacity());
        query.append(", "+room.getBed_number());
        query.append(", '"+room.getRoom_type()+"'");
        query.append(", "+room.getPrice()+");");

        return query.toString();
    }

    public void createButton(Rooms rooms) throws Exception {
        Statement statement = connection.createStatement();
        int affectedRows = statement.executeUpdate(roomsQuery(rooms));
        if(affectedRows<=0) throw new Exception("Failed");
        System.out.println("Done");
        roomNumber.clear();
        floorNumber.clear();
        roomCapacity.clear();
        bedNumber.clear();
        roomType.clear();
        Price.clear();
    }
}
}