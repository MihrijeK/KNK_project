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
    }
