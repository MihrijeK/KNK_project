package sample.Controllers;

import DatabaseConnection.dbConnection;
import Helpers.Rooms;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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