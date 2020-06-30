package sample.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Helpers.Service_Type;
import sample.Repositories.*;
import Helpers.Rooms;
import javafx.fxml.Initializable;
import DatabaseConnection.*;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;


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
    private double total = 0;

    //klienti qe po paguan
    private int user =1;
    private int payment_id=1;

//    public void setUser(int user) {
//        this.user = user;
//    }
//
//    public void setPaymentID(int id) {
//        this.payment_id = id;
//    }


    ObservableList<Rooms> oblist = FXCollections.observableArrayList();
    ObservableList<Service_Type> oblist1 = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            connection = dbConnection.getConnection();
            //Fatura per dhomat
            PaymentsRepository.roomsBill(user, payment_id,  oblist, total);

            //Fatura per sherbimet
            PaymentsRepository.servicesBill(user, payment_id, oblist1, total);

            //Te dhenat e klientit
            PaymentsRepository.guestInfo(user, personalNr, emriMbiemri);

            totali.setText(total + "€");
        } catch (Exception ex) {
            Logger.getLogger(PaymentView.class.getName()).log(Level.SEVERE, null, ex);
        }

        firstColumn.setCellValueFactory(new PropertyValueFactory<>("room_number"));
        secondColumn.setCellValueFactory(new PropertyValueFactory<>("room_type"));
        thirdColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        fourthColumn.setCellValueFactory(new PropertyValueFactory<>("service_name"));
        fifthColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        tableView.setItems(oblist); //tabela per faturen e dhomave
        tableView1.setItems(oblist1); //tabela per faturen e sherbimeve

        toggle = new ToggleGroup();
        this.cash.setToggleGroup(toggle);
        this.creditCard.setToggleGroup(toggle);
        this.gift.setToggleGroup(toggle);
    }

    public void paguaj() throws Exception {
        try {
            RadioButton selectedMethod = (RadioButton) toggle.getSelectedToggle();
            String metodaEzgjedhur = selectedMethod.getText();
            PaymentsRepository.updatePayments(user, payment_id, metodaEzgjedhur); //query per te kryer pagesen

            if(total != 0) {
                Alert konfirmimi = new Alert(Alert.AlertType.INFORMATION);
                konfirmimi.setTitle("Confirmation");
                konfirmimi.setHeaderText("Payment Done");
                konfirmimi.setContentText("Payment is completed successfully");
                konfirmimi.showAndWait();

                //Empty all the fields
                emriMbiemri.setText("");
                personalNr.setText("");
                tableView.getItems().clear();
                tableView1.getItems().clear();
                totali.setText("");
                //total = 0; //nevojitet per te verifikuar se nuk ka asnje rezultat nga db
                toggle.getToggles().clear();
            }else {
                Alert nothing = new Alert(Alert.AlertType.WARNING);
                nothing.setTitle("Empty");
                nothing.setHeaderText(null);
                nothing.setContentText("Nothing to pay");
                nothing.showAndWait();

                //Empty all the fields
                emriMbiemri.setText("");
                personalNr.setText("");
                tableView.getItems().clear();
                tableView1.getItems().clear();
                totali.setText("");
                //total = 0; //nevojitet per te verifikuar se nuk ka asnje rezultat nga db
                toggle.getToggles().clear();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            errorHandle.setText("Ju lutem zgjedhni \nnjeren nga metodat \ne pageses!");
        }
    }
}
