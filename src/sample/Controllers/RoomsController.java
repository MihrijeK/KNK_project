package controllers;

import Connectivity.dbConnection;
import Connectivity.dbConnection;
import Helpers.Room;
import Helpers.Rooms;
import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.event.ActionEvent;
import sample.Controller;

import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class RoomsController implements Initializable {
    @FXML private DatePicker firstDatePickerField;
    @FXML private DatePicker lastDatePickerField;
    @FXML private Button findButtonId;
    @FXML private TableView<Rooms> tableView;
    @FXML private TableColumn<Rooms,Integer> roomNumberCol;
    @FXML private TableColumn<Rooms,Integer> roomFloorCol;
    @FXML private TableColumn<Rooms,Integer> capacityCol;
    @FXML private TableColumn<Rooms,Integer> bedsCol;
    @FXML private TableColumn<Rooms,String> roomTypeCol;
    @FXML private TableColumn<Rooms,Integer> priceCol;
    @FXML private Button makeReservation;
    @FXML private Button cancelButton;

    dbConnection connectionClass = new dbConnection();
    Connection connection;

    ObservableList<Rooms> roomList=FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            connection= connectionClass.getConnection();

            setDefaultDate();
            String defaultFirstDate=firstDatePickerField.getValue().toString();
            String defaultLastDate=lastDatePickerField.getValue().toString();
            loadAvailableRooms(defaultFirstDate,defaultLastDate,connection);

        }catch(Exception e){}

    }

    public void findButtonClicked(ActionEvent actionEvent) {
        try {
            String newFirstDate=firstDatePickerField.getValue().toString();
            String newLastDate=lastDatePickerField.getValue().toString();

            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
            Date firstDate=format.parse(newFirstDate);
            Date lastDate=format.parse(newLastDate);

            if(firstDate.compareTo(lastDate)<0){
                loadAvailableRooms(newFirstDate,newLastDate,connection);
            }else{
                Alert alertBox=new Alert(Alert.AlertType.INFORMATION);
                alertBox.setContentText("Dates you entered are not valid!");
                alertBox.showAndWait();
            }

        }catch (Exception e){

        }
    }

    public void onMakeReservationButtonClicked(ActionEvent actionEvent){
        TableView.TableViewSelectionModel<Rooms> selectionModel=tableView.getSelectionModel();
        ObservableList<Rooms> roomsToBook = selectionModel.getSelectedItems();

        if(roomsToBook.isEmpty()){
            Alert noRoomsSelected=new Alert(Alert.AlertType.INFORMATION);
            noRoomsSelected.setContentText("No rooms selected!\nSelect a room to make a reservation.");
            noRoomsSelected.showAndWait();
        }else{
            try {
                URL url = new File("src/views/reservation.fxml").toURI().toURL();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(url);
                Pane newScreen = loader.load();
                ReservationsController reservationsController=loader.getController();
                reservationsController.getRooms(roomsToBook,firstDatePickerField.getValue(),lastDatePickerField.getValue());

                Scene scene=new Scene(newScreen);
                Stage stage=(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    public void onCancelButtonClicked(ActionEvent actionEvent){
        TableView.TableViewSelectionModel<Rooms> selectionModel=tableView.getSelectionModel();
        selectionModel.clearSelection();
    }

    private void setDefaultDate(){
        Instant week=Instant.now().plus(7,ChronoUnit.DAYS);
        LocalDate currentLocalDate=LocalDate.now();
        LocalDate localDateFromAWeek=LocalDate.ofInstant(week, ZoneOffset.UTC);
        firstDatePickerField.setValue(currentLocalDate);
        lastDatePickerField.setValue(localDateFromAWeek);
    }

    private void loadAvailableRooms(String firstDate,String lastDate,Connection connection) throws Exception{
        roomList.clear();
        String query="select * \n" +
                "from rooms r\n" +
                "where r.room_number not in(select r.room_number \n" +
                "from reservations res inner join rooms r on res.room_id=r.room_number\n" +
                "where checkin_date='"+firstDate+"' or checkout_date='"+lastDate+"')";

        Statement stmt=connection.createStatement();
        ResultSet rs=stmt.executeQuery(query);

        while(rs.next()){
            roomList.add(new Rooms(rs.getInt("room_number"),rs.getInt("floor_number"),rs.getInt("capacity"),
                    rs.getInt("bed_number"),rs.getString("room_type"),rs.getDouble("price")));
        }


        tableView.setPlaceholder(new Label("No rooms available"));
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        onClickSelect();

        roomNumberCol.setCellValueFactory(new PropertyValueFactory<>("room_number"));
        roomFloorCol.setCellValueFactory(new PropertyValueFactory<>("floor_number"));
        capacityCol.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        bedsCol.setCellValueFactory(new PropertyValueFactory<>("bed_number"));
        roomTypeCol.setCellValueFactory(new PropertyValueFactory<>("room_type"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

//        reservationCol.setCellValueFactory(new PropertyValueFactory<Rooms,Boolean>());
//        reservationCol.setCellFactory( tc -> new CheckBoxTableCell<Rooms,Boolean>());
//        reservationCol.setCellFactory(e->{
//            return new CheckBoxTableCell<>();
//        });


        tableView.setItems(roomList);
    }

    private void onClickSelect(){
        tableView.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
            Node node = event.getPickResult().getIntersectedNode();

            while (node != null && node != tableView && !(node instanceof TableRow)) {
                node = node.getParent();
            }

            if (node instanceof TableRow) {
                event.consume();

                TableRow row = (TableRow) node;
                TableView tv = row.getTableView();

                tv.requestFocus();

                if (!row.isEmpty()) {
                    int index = row.getIndex();
                        tv.getSelectionModel().select(index);
                }
            }
        });
    }


}
