package sample.Controllers;

import Helpers.Staff;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import DatabaseConnection.*;

public class EditStaffMemberView {

    private Staff staff;
    @FXML
    private TextField first_name;
    @FXML
    private TextField last_name;
    @FXML
    private TextField personal_number;
    @FXML
    private TextField phone_number;
    @FXML
    private DatePicker birthdate;
    @FXML
    private ChoiceBox position;
    @FXML
    private Label salary;
    @FXML
    private PasswordField password;
    @FXML
    private Button editStaffMemberBtn;
    @FXML
    private Button cancleCreation;

    private Connection connection = dbConnection.getConnection();

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    private final Stage stage;
