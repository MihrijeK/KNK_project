package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MainManagingController implements Initializable {
    @FXML private Button mainBtn;
    @FXML private Button reservationsBtn;
    @FXML private Button paymentsBtn;
    @FXML private Button servicesBtn;
    @FXML private Pane mainPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
            else if(actionEvent.getSource()==servicesBtn){
                viewLoader("Services");
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
                URL url = new File("src/views/rooms.fxml").toURI().toURL();
                loader.setLocation(url);
                node = loader.load();
                break;
            case "Payments":
                //dritarja payments e kushtrimit
                break;
            case "Services":
                //dritarja services e mires
                break;
            default:
                node=null;
                System.out.println("No such view!");
        }
        mainPane.getChildren().clear();
        mainPane.getChildren().add(node);
    }
}
