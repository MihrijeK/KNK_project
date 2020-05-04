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

    public void handleButtonAction(ActionEvent actionEvent)throws Exception {
        if(actionEvent.getSource() == overviewBtn){
            overviewPane.toFront();
        }else if(actionEvent.getSource() == staffBtn){
            staffPane.toFront();
            CreateMemberBtn.setOnAction(e-> {
                CreateStaffMemberView cw = null;
                try {
                    cw = new CreateStaffMemberView();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                try {
                    cw.display();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            });
        }else if(actionEvent.getSource() == roomsBtn){
            roomsPane.toFront();
            btnAddNewRoom.setOnAction(e->{
                try {
                    AddNewRoomView addNewRoomView = new AddNewRoomView();
                    addNewRoomView.display();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
        }
    }

    private void addButtonToTable() {
        TableColumn<Staff, Void> colBtn = new TableColumn("Edit");

        Callback<TableColumn<Staff, Void>, TableCell<Staff, Void>> cellFactory = new Callback<TableColumn<Staff, Void>, TableCell<Staff, Void>>() {
            @Override
            public TableCell<Staff, Void> call(final TableColumn<Staff, Void> param) {
                final TableCell<Staff, Void> cell = new TableCell<Staff, Void>() {
                    private final Button btn = new Button("Edit");
                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Staff data = getTableView().getItems().get(getIndex());
                            EditStaffMemberView ew = null;
                            try {
                                ew = new EditStaffMemberView(data);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            ew.display();
                        });
                    }
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        colBtn.setCellFactory(cellFactory);
        showStaffTable.getColumns().add(colBtn);
    }

    ObservableList<Staff> staffObservableList = FXCollections.observableArrayList();
    private void setSaffTableViewContent(TableColumn<Staff, Integer> col_id,TableColumn<Staff, String> col_fname,TableColumn<Staff, String> col_lname,TableColumn<Staff, Integer> col_prsNum,TableColumn<Staff,
            String> col_position,TableColumn<Staff, Date> col_bday,TableColumn<Staff, String> col_phone,TableColumn<Staff, Integer> col_salary){
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_fname.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        col_lname.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        col_prsNum.setCellValueFactory(new PropertyValueFactory<>("personalNumber"));
        col_position.setCellValueFactory(new PropertyValueFactory<>("position"));
        col_bday.setCellValueFactory(new PropertyValueFactory<>("birthdate"));
        col_phone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        col_salary.setCellValueFactory(new PropertyValueFactory<>("salary"));

        try {
            String query = "SELECT * FROM staff";
            Statement s = connection.createStatement();
            ResultSet r = s.executeQuery(query);

            while (r.next()){
                staffObservableList.add(new Staff(r.getInt("id"),
                        r.getString("first_name"),
                        r.getString("last_name"),
                        r.getInt("personal_number"),
                        r.getString("position"),
                        r.getString("gender"),
                        r.getDate("birthdate"),
                        r.getString("phone_number"),
                        r.getInt("salary"),
                        r.getString("passwordd")));
            };
            addButtonToTable();
            showStaffTable.setItems(staffObservableList);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    ObservableList<Rooms> roomsObservableListView  = FXCollections.observableArrayList();
    private void setRoomsTableViewContetn(TableColumn<Rooms, Integer> roomNumber,TableColumn<Rooms, Integer> floorNumber,TableColumn<Rooms, Integer> capacity,TableColumn<Rooms, Integer> bedNumber,
                                          TableColumn<Rooms, String> roomType ,TableColumn<Rooms, Double> price){
        roomNumber.setCellValueFactory(new PropertyValueFactory<>("room_number"));
        floorNumber.setCellValueFactory(new PropertyValueFactory<>("floor_number"));
        capacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        bedNumber.setCellValueFactory(new PropertyValueFactory<>("bed_number"));
        roomType.setCellValueFactory(new PropertyValueFactory<>("room_type"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        try {
            String query = "SELECT * FROM rooms";
            Statement s = connection.createStatement();
            ResultSet r = s.executeQuery(query);

            while (r.next()){
                roomsObservableListView.add(new Rooms(r.getInt("room_number"),
                        r.getInt("floor_number"),
                        r.getInt("capacity"),
                        r.getInt("bed_number"),
                        r.getString("room_type"),
                        r.getDouble("price")));
            };
            roomsTableView.setItems(roomsObservableListView);
            addEditRoomBtn();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void addEditRoomBtn() {
        TableColumn<Rooms, Void> colBtn = new TableColumn("Edit");

        Callback<TableColumn<Rooms, Void>, TableCell<Rooms, Void>> cellFactory = new Callback<TableColumn<Rooms, Void>, TableCell<Rooms, Void>>() {
            @Override
            public TableCell<Rooms, Void> call(final TableColumn<Rooms, Void> param) {
                final TableCell<Rooms, Void> cell = new TableCell<Rooms, Void>() {
                    private final Button btn = new Button("Edit");
                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Rooms data = getTableView().getItems().get(getIndex());
                            EditRoomsView ew = null;
                            try {
                                ew = new EditRoomsView(data);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            try {
                                ew.display();
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        });
                    }
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        colBtn.setCellFactory(cellFactory);
        roomsTableView.getColumns().add(colBtn);
    }
}