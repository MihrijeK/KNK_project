package sample.Controllers;

import Helpers.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import sample.Controllers.Admin.RoomControllers.AddNewRoomView;
import sample.Controllers.Admin.Services.AddServicesType;
import sample.Controllers.Admin.StaffControllers.CreateStaffMemberView;
import sample.Controllers.Partials.AddButton;
import sample.Models.View.TableViewContent;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;


public class AdminDashboard implements Initializable {


    @FXML private Button overviewBtn;
    @FXML private Button staffBtn;
    @FXML private Button roomsBtn;
    @FXML private Button paymentsBtn;
    @FXML private Button reservationsBtn;
    @FXML private Button sevicesBtn;
    @FXML private Button settingsBtn;
    @FXML private Button logoutBtn;

    @FXML private AnchorPane overviewPane;

    @FXML private AnchorPane staffPane;
    @FXML private Button CreateMemberBtn;
    @FXML private Button refreshTableView;

    @FXML private TableView<Staff> showStaffTable;
    @FXML private TableColumn<Staff, Integer> col_id;
    @FXML private TableColumn<Staff, String> col_fname;
    @FXML private TableColumn<Staff, String> col_lname;
    @FXML private TableColumn<Staff, Integer> col_prsNum;
    @FXML private TableColumn<Staff, String> col_position;
    @FXML private TableColumn<Staff, Date> col_bday;
    @FXML private TableColumn<Staff, String> col_phone;
    @FXML private TableColumn<Staff, Integer> col_salary;
    @FXML private TableColumn gender;
    @FXML private ChoiceBox positionCB;
    @FXML private Button positionFilterBtn;
    public ObservableList<Staff> staffi = null;


    @FXML public AnchorPane roomsPane;
    @FXML private Button btnAddNewRoom;

    @FXML private TableView<Rooms> roomsTableView;
    @FXML private TableColumn<Rooms, Integer> roomNumber;
    @FXML private TableColumn<Rooms, Integer> floorNumber;
    @FXML private TableColumn<Rooms, Integer> capacity;
    @FXML private TableColumn<Rooms, Integer> bedNumber;
    @FXML private TableColumn<Rooms, String> roomType;
    @FXML private TableColumn<Rooms, Double> price;
    @FXML private ChoiceBox<String> roomNumberFilter;
    @FXML private ChoiceBox<String> roomCapacityFilter;
    @FXML private ChoiceBox<String> roomTypeFilter;
    @FXML private Button btnRefresh;
    @FXML private Button findRooms;

    public ObservableList<Rooms> room = null;


    @FXML private AnchorPane servicesPane;
    @FXML private Button addNewServiceBtn;

    @FXML private TableView<Service_Type> servicesTableView;
    @FXML private TableColumn<Service_Type, Integer> serviceID;
    @FXML private TableColumn<Service_Type, String> serviceName;
    @FXML private TableColumn<Service_Type, Double> servicePrice;
    @FXML private TableColumn<Service_Type, Integer> serviceQuantity;
    public ObservableList<Service_Type> serviceObservableList = null;


    public void handleButtonAction(ActionEvent actionEvent) throws Exception {

        if (actionEvent.getSource() == overviewBtn) {
            overviewPane.toFront();
        } else if (actionEvent.getSource() == staffBtn) {
            staffPane.toFront();
            CreateMemberBtn.setOnAction(e -> {
                CreateStaffMemberView cw = null;
                try {
                    cw = new CreateStaffMemberView();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                try {
                    cw.display(staffi);
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            });
            positionFilterBtn.setOnAction(e -> {
                staffi = TableViewContent.setSaffByPosition(col_id, col_fname, col_lname, col_prsNum, col_position, col_bday, col_phone, col_salary,gender,positionCB);
                showStaffTable.setItems(staffi);
            });

        } else if (actionEvent.getSource() == roomsBtn) {
            roomsPane.toFront();
            btnAddNewRoom.setOnAction(e -> {
                try {
                    AddNewRoomView addNewRoomView = new AddNewRoomView();
                    addNewRoomView.display(room);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
            findRooms.setOnAction(e -> {
                roomsTableView.setItems(TableViewContent.setRoomsByType(roomNumber, floorNumber, capacity, bedNumber, roomType, price,roomTypeFilter,roomNumberFilter,roomCapacityFilter));
            });
            btnRefresh.setOnAction(e->{
                showStaffTable.setItems(TableViewContent.setSaff(col_id, col_fname, col_lname, col_prsNum, col_position, col_bday, col_phone, col_salary,gender));
                roomNumberFilter.setValue(null);
                roomCapacityFilter.setValue(null);
                roomTypeFilter.setValue(null);
            });
        }else if (actionEvent.getSource() == sevicesBtn) {
            servicesPane.toFront();
            addNewServiceBtn.setOnAction(e -> {
                try {
                    AddServicesType ast = new AddServicesType();
                    ast.display(serviceObservableList);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        positionCB.getItems().addAll("Manager","Waiter","Recepsionist");
        roomTypeFilter.getItems().addAll("All","Single","Double","Triple","Quad","Double-double","Master Suite","Junior Suite");
        roomNumberFilter.getItems().addAll("All","1","2","3","4","5","6");
        roomCapacityFilter.getItems().addAll("All","1","2","3","4","5");

        staffi = TableViewContent.setSaff(col_id, col_fname, col_lname, col_prsNum, col_position, col_bday, col_phone, col_salary,gender);
        showStaffTable.setItems(staffi);
        AddButton.addEditStaffBtn(showStaffTable,"Edit",staffi);
        AddButton.addDeleteStaffBtn(showStaffTable,"Delete");

        room = TableViewContent.setRooms(roomNumber, floorNumber, capacity, bedNumber, roomType, price);
        roomsTableView.setItems(room);
        AddButton.addEditRoomBtn(roomsTableView,"Edit",room);
        AddButton.addDeleteRoomBtn(roomsTableView,"Delete");

        serviceObservableList = TableViewContent.setServices(serviceID,serviceName,servicePrice,serviceQuantity);
        servicesTableView.setItems(serviceObservableList);
        AddButton.addEditServiceTypeBtn(servicesTableView,"Edit",serviceObservableList);
        AddButton.addDeleteServiceTypeBtn(servicesTableView,"Delete");
    }
}

