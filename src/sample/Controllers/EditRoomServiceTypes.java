package sample.Controllers;

import  DatabaseConnection.dbConnection;
import Helpers.Service_Type;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class EditRoomServiceTypes {
  
    private Connection connection = dbConnection.getConnection();
  
    private final Stage stage;
  
    private Service_Type serviceType;
  
    @FXML private TextField price;
    @FXML private TextField serviceName;
    @FXML private TextField quantity;
    @FXML private Button updateButton;
    @FXML private Button cancleButton;
  
}
