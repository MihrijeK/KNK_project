package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MainManagingController extends LanguageController{
    @FXML private Button mainBtn;
    @FXML private Button reservationsBtn;
    @FXML private Button paymentsBtn;
    @FXML private Button logOutBtn;
    @FXML private Pane mainPane;
    @FXML private Label loggedInUser;
    @FXML private MenuItem logoutButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadLangTexts(getLangBundle());
        try{
            viewLoader("Reservations");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void onButtonClicked(ActionEvent actionEvent){
        try{
            if(actionEvent.getSource()==mainBtn){
                viewLoader("Main");
            }
            else if(actionEvent.getSource()==reservationsBtn){
                viewLoader("Reservations");
            }
            else if(actionEvent.getSource()==paymentsBtn){
                viewLoader("Payments");
            }
            else if(actionEvent.getSource()==logOutBtn){
                FXMLLoader loader=new FXMLLoader();
                URL url=new File("src/sample/Views/login.fxml").toURI().toURL();
                loader.setLocation(url);
                Pane pane=loader.load();
                Scene scene=new Scene(pane);
                Stage stage=(Stage)((javafx.scene.Node)actionEvent.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Sun Hotel");
                stage.show();
            }else{
                //do smth
            }
        }catch(Exception e){
            Alert alertBox=new Alert(Alert.AlertType.ERROR);
            alertBox.setContentText(e.getMessage());
            alertBox.showAndWait();
        }

    }

    public void viewLoader(String view) throws Exception{
        FXMLLoader loader=new FXMLLoader();
        Parent node=null;
        switch(view) {
            case "Main":
                //dritarja main e mires
                break;
            case "Reservations":
                URL urlres = new File("src/sample/Views/rooms.fxml").toURI().toURL();
                loader.setLocation(urlres);
                node = loader.load();
                break;
            case "Payments":
                URL urlpay = new File("src/sample/Views/Payments.fxml").toURI().toURL();
                loader.setLocation(urlpay);
                node = loader.load();
                break;
            default:
                node=null;
                System.out.println("No such view!");
        }
        mainPane.getChildren().clear();
        mainPane.getChildren().add(node);
    }

    @FXML
    private void insertGuestMenuClicked(ActionEvent actionEvent){
        try{
            FXMLLoader loader=new FXMLLoader();
            URL url1 = new File("src/sample/Views/InsertGuest.fxml").toURI().toURL();
            loader.setLocation(url1);
            Pane pane=loader.load();
            Scene scene=new Scene(pane);
            Stage stage=new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.show();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }

    @FXML
    private void logoutMenuItemClicked(ActionEvent actionEvent){
        try{
            FXMLLoader loader=new FXMLLoader();
            URL url=new File("src/sample/Views/login.fxml").toURI().toURL();
            loader.setLocation(url);
            Pane pane=loader.load();
            Scene scene=new Scene(pane);
            Stage stage=(Stage)((javafx.scene.Node)actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Sun Hotel");
            stage.show();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void getUser(String emri,String mbiemri){
        loggedInUser.setText("Logged In: "+emri+" "+mbiemri);
    }

    @Override
    public void loadLangTexts(ResourceBundle langBundle) {
        mainBtn.setText(langBundle.getString("overviewButton"));
        reservationsBtn.setText(langBundle.getString("reservationButton"));
        paymentsBtn.setText(langBundle.getString("paymentsButton"));
        logOutBtn.setText(langBundle.getString("logoutButton"));
    }
}
