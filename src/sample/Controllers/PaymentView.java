package sample.Controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Helpers.Service_Type;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
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


public class PaymentView extends LanguageController {

    @FXML private Label lbl_paymentDetail;
    @FXML private Label lbl_total;
    @FXML private Label lbl_payType;
    @FXML private Label lbl_name;

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
    @FXML private AnchorPane anchor;
    private ToggleGroup toggle;
    private double total;

    //klienti qe po paguan
    private static int user;
    private static int payment_id;

    public static void setUser(int user1) {
        user = user1;
    }

   public static void setPaymentID(int id) {
       payment_id = id;
   }

    ObservableList<Rooms> oblist = FXCollections.observableArrayList();
    ObservableList<Service_Type> oblist1 = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadLangTexts(getLangBundle());
        try {
            connection = dbConnection.getConnection();
            //Fatura per dhomat
            double total1 = PaymentsRepository.roomsBill(user, payment_id,  oblist);

            //Fatura per sherbimet
            double total2 = PaymentsRepository.servicesBill(user, payment_id, oblist1);

            //Te dhenat e klientit
            PaymentsRepository.guestInfo(user, personalNr, emriMbiemri);

            total = total1 + total2;
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
                URL url = new File("src/sample/Views/main-manager.fxml").toURI().toURL();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(url);
                Pane newScreen = loader.load();

                Scene scene=new Scene(newScreen);
                Stage stage=(Stage)anchor.getScene().getWindow();
                stage.setScene(scene);
                stage.show();

                Alert konfirmimi = new Alert(Alert.AlertType.INFORMATION);
                konfirmimi.setTitle("Confirmation");
                konfirmimi.setHeaderText("Payment Done");
                konfirmimi.setContentText("Payment is completed successfully");
                konfirmimi.showAndWait();
            }else {
                URL url = new File("src/sample/Views/main-manager.fxml").toURI().toURL();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(url);
                Pane newScreen = loader.load();

                Scene scene=new Scene(newScreen);
                Stage stage=(Stage)anchor.getScene().getWindow();
                stage.setScene(scene);
                stage.show();

                Alert nothing = new Alert(Alert.AlertType.WARNING);
                nothing.setTitle("Empty");
                nothing.setHeaderText(null);
                nothing.setContentText("Nothing to pay");
                nothing.showAndWait();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            errorHandle.setText("Ju lutem zgjedhni \nnjeren nga metodat \ne pageses!");
        }
    }

    @Override
    public void loadLangTexts(ResourceBundle langBundle) {
        lbl_paymentDetail.setText(langBundle.getString("paymentDetail"));
        lbl_total.setText(langBundle.getString("total"));
        lbl_payType.setText(langBundle.getString("payType"));
        lbl_name.setText(langBundle.getString("first_name"));

        firstColumn.setText(langBundle.getString("room"));
        secondColumn.setText(langBundle.getString("roomType"));
        thirdColumn.setText(langBundle.getString("price"));

        fourthColumn.setText(langBundle.getString("service_name"));
        fifthColumn.setText(langBundle.getString("price"));
    }
}
