package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;

public class InsertGuestController implements Initializable {
    @FXML private TextField firstName;
    @FXML private TextField lastName;
    @FXML private TextField personalNumber;
    @FXML private TextField phoneNumber;
    @FXML private DatePicker birthDate;
    @FXML private RadioButton male;
    @FXML private RadioButton female;
    @FXML private ToggleGroup genderToggleGroup;    
    
    private Connection conn;
    public void insertGuest() throws Exception {
        if(validateInput()){
            conn = DatabaseConnection.dbConnection.getConnection();
            boolean rs = conn.createStatement().execute(createQuery());
            if(rs){
                alert("error" , "Guest Register", "Something has gone wrong, try again!");
            }else{
                alert("confirmation","Guest Register","The new guest has been succsefully registerd");  
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
            alert("warning","Guest Register", "All fields must be filled or selected!");
            return false;
        }else if(!isInt(personalNumber.getText())){
            alert("warning","Guest Register", "Personal number field must be filled only with numbers!");
            return false;
        }else if(!isInt(phoneNumber.getText())){
            alert("warning","Guest Register", "Phone number field must be filled only with numbers!");
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
    public String createQuery(){
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO guests(first_name,last_name,personal_number,birthdate,phone_number,gender,registred_date)");
        sb.append("VALUES('");
        sb.append(firstName.getText());
        sb.append("','");
        sb.append(lastName.getText());
        sb.append("',");
        sb.append(Integer.parseInt(personalNumber.getText()));
        sb.append(",");
        sb.append("date_format('");
        sb.append(returnDate());
        sb.append("','%Y-%m-%d')");
        sb.append(",'");
        sb.append(phoneNumber.getText());
        sb.append("',");
        if(genderToggleGroup.getSelectedToggle().equals(male)) {
            sb.append("'Male'");
        }else{
            sb.append("'Female'");
        }
        sb.append(",");
        sb.append("now())");
        return sb.toString();
    }
    public String returnDate(){
        StringBuilder sb = new StringBuilder();
        sb.append(birthDate.getValue().getYear());
        sb.append("-");
        sb.append(birthDate.getValue().getMonthValue());
        sb.append("-");
        sb.append(birthDate.getValue().getDayOfMonth());
        return sb.toString();
    }
    public void alert(String alertType,String title, String message){
        Alert alert = null;
        if(alertType == "warning"){
            alert = new Alert(Alert.AlertType.WARNING);
        }else if(alertType == "confirmation") {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
        }else if(alertType == "error"){
            alert = new Alert(Alert.AlertType.ERROR);
        }
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
    public void closeMe(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        genderToggleGroup = new ToggleGroup();
        this.male.setToggleGroup(this.genderToggleGroup);
        this.female.setToggleGroup(this.genderToggleGroup);
    }
}
