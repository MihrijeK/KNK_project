package sample.Controllers;

import Helpers.GuestPayment;
import Helpers.Payment;
import Helpers.Rooms;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import sample.Controllers.Partials.AddButton;
import sample.Repositories.PaymentsRepository;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

public class PaymentsController implements Initializable {
    @FXML private DatePicker datePicker;
    @FXML private Button searchBtn;
    @FXML private TableView<GuestPayment> paymentsTableView;
    @FXML private TableColumn<GuestPayment,Integer> payment_id;
    @FXML private TableColumn<GuestPayment,Integer> firstname;
    @FXML private TableColumn<GuestPayment,Integer> lastname;
    @FXML private TableColumn<GuestPayment,Integer> date;
    @FXML private TableColumn<GuestPayment,Integer> price;
    @FXML private AnchorPane anchor;

    Connection connection;
    ObservableList<GuestPayment> paymentList= FXCollections.observableArrayList();
    PaymentsRepository paymentsRepository;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setDefaultDate();
        loadPayments(anchor);
        AddButton.addPayButton(paymentsTableView,"Pay",anchor);
    }

    private void loadPayments(AnchorPane anchor){
        try{
            paymentList.clear();

            ResultSet rs=paymentsRepository.getUnpaidByDate(datePicker.getValue().toString());

            while(rs.next()){
                paymentList.add(new GuestPayment(rs.getInt("id"),rs.getString("first_name"),rs.getString("last_name"),
                        rs.getString("checkout_date"),rs.getDouble("price")));
            }

            paymentsTableView.setPlaceholder(new Label("No payments to be made"));

            payment_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            firstname.setCellValueFactory(new PropertyValueFactory<>("firstname"));
            lastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
            date.setCellValueFactory(new PropertyValueFactory<>("date"));
            price.setCellValueFactory(new PropertyValueFactory<>("price"));

            paymentsTableView.setItems(paymentList);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void onSerchButtonClicked(ActionEvent actionEvent) {
        System.out.println("Button Clicked!");
        loadPayments(anchor);
    }

    private void setDefaultDate(){
        LocalDate currentLocalDate=LocalDate.now();
        datePicker.setValue(currentLocalDate);
    }
    }
