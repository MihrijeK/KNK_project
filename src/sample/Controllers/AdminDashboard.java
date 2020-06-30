package sample.Controllers;

import Helpers.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.Components.ErrorPopupComponent;
import sample.Controllers.Admin.RoomControllers.AddNewRoomView;
import sample.Controllers.Admin.Services.AddServicesType;
import sample.Controllers.Admin.StaffControllers.CreateStaffMemberView;
import sample.Controllers.Partials.AddButton;
import sample.Models.View.ChartsView;
import sample.Models.View.PaymentModel;
import sample.Models.View.TableViewContent;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;


public class AdminDashboard extends LanguageController {


    @FXML private Button overviewBtn;
    @FXML private Button staffBtn;
    @FXML private Button roomsBtn;
    @FXML private Button paymentsBtn;
    @FXML private Button sevicesBtn;
    @FXML private Button logoutBtn;


    @FXML private AnchorPane staffPane;
    @FXML private Button CreateMemberBtn;
    @FXML private Button refreshTableView;

    @FXML private AnchorPane overviewPane;
    @FXML private GridPane chartGridPane;
    @FXML private PieChart serviceChart;
    @FXML private PieChart staffChart;
    @FXML private PieChart roomsChart;
    @FXML private PieChart roomsChart2;
    @FXML private Label statusi3;
    @FXML private Label statusi1;
    @FXML private Label statusi;
    @FXML private Label statusi2;
    
    
    @FXML private AnchorPane paymentsPane;
    @FXML private TableView<PaymentModel> paymentsTableView;
    @FXML private TableColumn<PaymentModel,Integer> payment_id;
    @FXML private TableColumn<PaymentModel,String> firstname;
    @FXML private TableColumn<PaymentModel,String> lastname;
    @FXML private TableColumn<PaymentModel,Date> date;
    @FXML private TableColumn<PaymentModel,Double> price1;
    @FXML private TableColumn<PaymentModel,Integer> isPayed;
    @FXML private DatePicker paymentDtPickerFilter;
    @FXML private Button paymentFilterBtn;
    public ObservableList<PaymentModel> paymentsObservableList = null;
    
    
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
                staffi = TableViewContent.setSaff(col_id, col_fname, col_lname, col_prsNum, col_position, col_bday, col_phone, col_salary,gender,positionCB);
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
        }else if (actionEvent.getSource() == paymentsBtn){
            paymentsPane.toFront();
            paymentFilterBtn.setOnAction(e->{
                if(paymentDtPickerFilter.getValue() != null){
                    paymentsTableView.setItems(TableViewContent.setPayments(payment_id,firstname,lastname,date,price1,isPayed,paymentDtPickerFilter));
                }
                else{
                    ErrorPopupComponent.show("DatePicker is null","DATEPICKER ERROR");
                }
            });
        }else if(actionEvent.getSource()==logoutBtn){
            FXMLLoader loader=new FXMLLoader();
            URL url=new File("src/sample/Views/login.fxml").toURI().toURL();
            loader.setLocation(url);
            Pane pane=loader.load();
            Scene scene=new Scene(pane);
            Stage stage=(Stage)((javafx.scene.Node)actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Sun Hotel");
            stage.show();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadLangTexts(getLangBundle());
        positionCB.getItems().addAll("All","Manager","Waiter","Recepsionist");
        positionCB.setValue("All");
        roomTypeFilter.getItems().addAll("All","Single","Double","Triple","Quad","Double-double","Master Suite","Junior Suite");
        roomNumberFilter.getItems().addAll("All","1","2","3","4","5","6");
        roomCapacityFilter.getItems().addAll("All","1","2","3","4","5");
        roomTypeFilter.setValue("All");
        roomNumberFilter.setValue("All");
        roomCapacityFilter.setValue("All");

        staffi = TableViewContent.setSaff(col_id, col_fname, col_lname, col_prsNum, col_position, col_bday, col_phone, col_salary,gender,positionCB);
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
        
        paymentsObservableList = TableViewContent.setPayments(payment_id,firstname,lastname,date,price1,isPayed,paymentDtPickerFilter);
        paymentsTableView.setItems(paymentsObservableList);

        try {
            chartGridPane.add(ChartsView.serviceChart(serviceChart,statusi),0,0);
            chartGridPane.add(ChartsView.staffChart(staffChart,statusi1),1,0);
            chartGridPane.add(ChartsView.roomsChart(roomsChart,statusi2),0,1);
            chartGridPane.add(ChartsView.roomFloorChart(roomsChart2,statusi3),1,1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadLangTexts(ResourceBundle langBundle) {
        payment_id.setText(langBundle.getString("payment_id"));
        firstname.setText(langBundle.getString("first_name"));
        lastname.setText(langBundle.getString("first_name"));
        date.setText(langBundle.getString("data"));
        price1.setText(langBundle.getString("price"));
        isPayed.setText(langBundle.getString("isPayed"));

        col_fname.setText(langBundle.getString("first_name"));
        col_lname.setText(langBundle.getString("last_name"));
        col_prsNum.setText(langBundle.getString("personal_number"));
        col_position.setText(langBundle.getString("position"));
        col_bday.setText(langBundle.getString("bday"));
        col_phone.setText(langBundle.getString("phone_number"));
        col_salary.setText(langBundle.getString("salary"));
        gender.setText(langBundle.getString("gender"));
        positionFilterBtn.setText(langBundle.getString("search"));

        findRooms.setText(langBundle.getString("search"));
        paymentFilterBtn.setText(langBundle.getString("search"));

        roomNumber.setText(langBundle.getString("roomNr"));
        floorNumber.setText(langBundle.getString("floorNr"));
        capacity.setText(langBundle.getString("roomCapacity"));
        bedNumber.setText(langBundle.getString("bedNumber"));
        roomType.setText(langBundle.getString("roomType"));
        price.setText(langBundle.getString("price"));

        overviewBtn.setText(langBundle.getString("overviewButton"));
        staffBtn.setText(langBundle.getString("staffButon"));
        roomsBtn.setText(langBundle.getString("roomsButton"));
        paymentsBtn.setText(langBundle.getString("paymentsButton"));
        sevicesBtn.setText(langBundle.getString("servicesButton"));
        logoutBtn.setText(langBundle.getString("logoutButton"));

        serviceName.setText(langBundle.getString("first_name"));
        servicePrice.setText(langBundle.getString("price"));
        serviceQuantity.setText(langBundle.getString("quantity"));
    }
}

