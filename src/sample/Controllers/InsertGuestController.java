import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML private TextField firstName;
    @FXML private TextField lastName;
    @FXML private TextField personalNumber;
    @FXML private TextField phoneNumber;
    @FXML private DatePicker birthDate;
    @FXML private RadioButton male;
    @FXML private RadioButton female;
    @FXML private ToggleGroup genderToggleGroup;    
    
    private Connection conn;
    public void insertGuest() throws SQLException, ClassNotFoundException, ParseException {
        if(validateInput()){
            conn = Database.getConnection();
            boolean rs = conn.createStatement().execute(createQuery());
            if(rs){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Guest Register");
                alert.setContentText("Something has gone wrong!");
                alert.setHeaderText(null);
                alert.show();
            }else{
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Guest Register");
                alert.setContentText("The new guest has been succsefully registerd");
                alert.setHeaderText(null);
                alert.show();   
            }
            clearText();
            conn.close();
        }else{
            clearText();
        }
    }
    public void clearText(){
        firstName.clear();
        lastName.clear();
        personalNumber.clear();
        phoneNumber.clear();
        birthDate.setValue(null);
        genderToggleGroup.selectToggle(null);
    }
    public boolean validateInput(){
        if(firstName.getText().isEmpty() || lastName.getText().isEmpty() || personalNumber.getText().isEmpty() || phoneNumber.getText().isEmpty()
        || birthDate.getValue() == null || genderToggleGroup.getSelectedToggle() == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Guest Register");
            alert.setContentText("The fields must not be empty");
            alert.setHeaderText(null);
            alert.showAndWait();
            return false;
        }else if(!isInt(personalNumber.getText())){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Guest Register");
            alert.setContentText("The fields must contain only numbers");
            alert.setHeaderText(null);
            alert.showAndWait();
            return false;
        }else if(!isInt(phoneNumber.getText())){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Guest Register");
            alert.setContentText("The fields must contain only numbers");
            alert.setHeaderText(null);
            alert.showAndWait();
            return false;
        }else{
            return true;
        }
    }
    public boolean isInt(String number){
        if(number == null){
            return false;
        }
        try {
            int newInt = Integer.parseInt(number);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        genderToggleGroup = new ToggleGroup();
        this.male.setToggleGroup(this.genderToggleGroup);
        this.female.setToggleGroup(this.genderToggleGroup);
    }
}
