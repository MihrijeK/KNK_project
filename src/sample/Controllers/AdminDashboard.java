package sample.Controllers;

import DatabaseConnection.dbConnection;
import Helpers.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;
import java.lang.String;


public class AdminDashboard implements Initializable{
    private Connection connection = dbConnection.getConnection();

    @FXML private Button overviewBtn;
    @FXML private Button staffBtn;
    @FXML private Button roomsBtn;
    @FXML private Button paymentsBtn;
    @FXML private Button reservationsBtn;
    @FXML private Button menusBtn;
    @FXML private Button settingsBtn;
    @FXML private Button logoutBtn;
    @FXML private AnchorPane overviewPane;
    @FXML private AnchorPane staffPane;

    @FXML private Button CreateMemberBtn;
    @FXML private Button refreshTableView;

    public AnchorPane roomsPane;
    @FXML
    private Button btnAddNewRoom;

    @FXML private TableView <Staff> showStaffTable;
    @FXML private TableColumn<Staff, Integer> col_id;
    @FXML private TableColumn<Staff,String> col_fname;
    @FXML private TableColumn<Staff,String> col_lname;
    @FXML private TableColumn<Staff, Integer> col_prsNum;
    @FXML private TableColumn<Staff,String> col_position;
    @FXML private TableColumn<Staff, Date> col_bday;
    @FXML private TableColumn<Staff,String> col_phone;
    @FXML private TableColumn<Staff, Integer> col_salary;

    @FXML private TableView <Rooms> roomsTableView;
    @FXML private TableColumn<Rooms, Integer> roomNumber;
    @FXML private TableColumn<Rooms, Integer> floorNumber;
    @FXML private TableColumn<Rooms, Integer> capacity;
    @FXML private TableColumn<Rooms, Integer> bedNumber;
    @FXML private TableColumn<Rooms, String> roomType;
    @FXML private TableColumn<Rooms, Double> price;

    public AdminDashboard() throws Exception {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setSaffTableViewContent(col_id,col_fname,col_lname,col_prsNum,col_position,col_bday,col_phone,col_salary);
        setRoomsTableViewContetn(roomNumber,floorNumber,capacity,bedNumber,roomType,price);

    }

}