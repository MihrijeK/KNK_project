package sample.Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import Helpers.Rooms;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import Helpers.Payment;
import DatabaseConnection.*;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class PaymentView implements Initializable {

    @FXML private Button pay;
    @FXML private Label totali;
    @FXML private Label emriMbiemri;
    @FXML private TableView<Rooms> tableView;
    @FXML private TableColumn<Rooms, Integer> firstColumn;
    @FXML private TableColumn<Rooms, String> secondColumn;
    @FXML private TableColumn<Rooms, Double> thirdColumn;
    @FXML private Connection connection;
    @FXML private RadioButton cash;
    @FXML private RadioButton creditCard;
    @FXML private RadioButton gift;
    private ToggleGroup toggle;
    private int total;

    //klienti qe po paguan
    private int user;
    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    ObservableList<Rooms> oblist = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            connection = dbConnection.getConnection();

            //Rezervimet qe i ka bere klienti ne fjale
            ResultSet tabela = connection.createStatement().executeQuery("");

            while(tabela.next()){
            }

            //Emri i atij qe po paguan
            ResultSet nameLastname = connection.createStatement().executeQuery("select first_name, last_name from guests\n" +
                    " where id="+user);
            while(nameLastname.next()){
                String rezultati = nameLastname.getString("first_name") + " " + nameLastname.getString("last_name");
                emriMbiemri.setText(rezultati);
            }

        } catch (Exception ex) {
            Logger.getLogger(PaymentView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void paguaj(ActionEvent actionEvent) throws Exception {
        
    }
}
