package sample.Controllers;

import Helpers.Payment;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
//import sample.Models.Payment;
//import sample.Models.View.TableViewContent;

import java.net.URL;
import java.util.ResourceBundle;

public class Payments implements Initializable {
  public Button overviewBtn;
    public Button staffBtn;
    public Button roomsBtn;
    public Button paymentsBtn;
    public Button reservationsBtn;
    public Button menusBtn;
    public Button settingsBtn;
    public Button logoutBtn;



    public AnchorPane paymentsPane;
//    public TableView<Payment> paymentsTableView;

//    public ObservableList<Payment> payments = null;
    public TableColumn payment_id;
    public TableColumn firstname;
    public TableColumn lastname;
    public TableColumn date;
    public TableColumn price;
    @FXML
    public TableColumn isPayed;
public Payments() throws Exception{

    }
  public void handleButtonAction(ActionEvent actionEvent) throws Exception {

        if (actionEvent.getSource() == paymentsBtn) {
            paymentsBtn.toFront();
        }

    }
     @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        payments = TableViewContent.setPayments(payment_id, firstname, lastname, date, price,isPayed);
//        paymentsTableView.setItems(payments);



    }
  
}
