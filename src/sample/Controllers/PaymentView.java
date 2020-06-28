package sample.Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import Helpers.Service_Type;
//import sample.Repositories.PaymentRepository;
import sample.Repositories.*;
import Helpers.Rooms;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import Helpers.Payment;
import DatabaseConnection.*;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class PaymentView implements Initializable {

    @FXML private Button pay;
    @FXML private Label totali;
    @FXML private Label errorHandle;
    @FXML private Label emriMbiemri;
    @FXML private Label personalNr;
    @FXML private TableView<Rooms> tableView;
    @FXML private TableColumn<Rooms, Integer> firstColumn;
    @FXML private TableColumn<Rooms, String> secondColumn;
    @FXML private TableColumn<Rooms, Double> thirdColumn;
    @FXML private TableView<Service_Type> tableView1;
    @FXML private TableColumn<Service_Type, String> fourthColumn;
    @FXML private TableColumn<Service_Type, String> fifthColumn;
    @FXML private Connection connection;
    @FXML private RadioButton cash;
    @FXML private RadioButton creditCard;
    @FXML private RadioButton gift;
    private ToggleGroup toggle;
    private int total;

    //klienti qe po paguan
    private int user;

//    public void setUser(int user) {
//        this.user = user;
//    }

    ObservableList<Rooms> oblist = FXCollections.observableArrayList();
    ObservableList<Service_Type> oblist1 = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            connection = dbConnection.getConnection();

            //Rezervimet qe i ka bere klienti ne fjale
            ResultSet tabela = PaymentsRepository.roomsBill(user);
            while(tabela.next()){
                oblist.add(new Rooms(tabela.getInt("room_number"),
                        tabela.getString("room_type"), tabela.getDouble("price")));
                total += tabela.getDouble("price");
            }

            ResultSet services = PaymentsRepository.servicesBill(user);
            while (services.next()){
                oblist1.add(new Service_Type(services.getString("service_name"), services.getDouble("price")));
                total += services.getDouble("price");
            }

            //Emri i atij qe po paguan
            ResultSet guestNameID = PaymentsRepository.guestInfo(user);
            while(guestNameID.next()){
                String rezultati = guestNameID.getString("first_name") + " " + guestNameID.getString("last_name");
                personalNr.setText(guestNameID.getString("personal_number"));
                emriMbiemri.setText(rezultati);
            }
            totali.setText(total + "€");
        } catch (Exception ex) {
            Logger.getLogger(PaymentView.class.getName()).log(Level.SEVERE, null, ex);
        }
        firstColumn.setCellValueFactory(new PropertyValueFactory<>("room_number"));
        secondColumn.setCellValueFactory(new PropertyValueFactory<>("room_type"));
        thirdColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        fourthColumn.setCellValueFactory(new PropertyValueFactory<>("service_name"));
        fifthColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        tableView.setItems(oblist);
        tableView1.setItems(oblist1);

        toggle = new ToggleGroup();
        this.cash.setToggleGroup(toggle);
        this.creditCard.setToggleGroup(toggle);
        this.gift.setToggleGroup(toggle);
    }

    public void paguaj(ActionEvent actionEvent) throws Exception {
        try {
            RadioButton selectedMethod = (RadioButton) toggle.getSelectedToggle();
            String metodaEzgjedhur = selectedMethod.getText();
            ResultSet rooms = PaymentsRepository.roomsBill(user);
            ResultSet services = PaymentsRepository.servicesBill(user);

            PaymentsRepository.updatePayments(user, metodaEzgjedhur);

            if(rooms.next() || services.next()) {
                Alert konfirmimi = new Alert(Alert.AlertType.INFORMATION);
                konfirmimi.setTitle("Confirmation");
                konfirmimi.setHeaderText("Payment Done");
                konfirmimi.setContentText("Payment is completed successfully");
                konfirmimi.showAndWait();

                emriMbiemri.setText("");
                personalNr.setText("");
                tableView.getItems().clear();
                tableView1.getItems().clear();
                totali.setText("");
                toggle.getToggles().clear();
            }else {
                Alert nothing = new Alert(Alert.AlertType.WARNING);
                nothing.setTitle("Empty");
                nothing.setHeaderText(null);
                nothing.setContentText("Nothing to pay");
                nothing.showAndWait();

                emriMbiemri.setText("");
                personalNr.setText("");
                tableView.getItems().clear();
                tableView1.getItems().clear();
                totali.setText("");
                toggle.getToggles().clear();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            errorHandle.setText("Ju lutem zgjedhni \nnjeren nga metodat \ne pageses!");
        }
    }
}
