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

}