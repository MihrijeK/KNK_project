package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.sql.*;
import  DatabaseConnection.dbConnection;
import Helpers.*;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;

public class Controller {
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField personalNumber;
    @FXML
    private DatePicker birthdate;
    @FXML
    private TextField phoneNumber;

    private Connection connection;

    public void onButtonClick(ActionEvent actionEvent) throws Exception {
        try {
            initDB();
        } catch (Exception e) {
            printError(e);
        }
        Statement statement = connection.createStatement();
        Guests g = getGuestInfo(firstName,lastName,personalNumber,phoneNumber,birthdate);


        String query = String.format("INSERT INTO guests(first_name,last_name,personal_number,birthdate,phone_number,registred_date) VALUES ('%s','%s',%d,%tD,'%s',%tD)",
                g.getFirstName(),g.getLastName(),g.getPersonalNumber(),g.getBirthdate(),g.getPhoneNumber(),g.getRegisteredDate());
        int affectedRows = statement.executeUpdate(query,Statement.RETURN_GENERATED_KEYS);

        if(affectedRows<= 0) throw new Exception("No row created");
        System.out.println("Done");
    }

    private Guests getGuestInfo(TextField firstName,TextField lastName,TextField personalNumber,TextField phoneNumber,DatePicker datePicker){
        String fName = firstName.getText();
        String lName = lastName.getText();
        String personalNum = personalNumber.getText();
        int num = Integer.parseInt(personalNum);
        String phoneNum = phoneNumber.getText();

        Date date =Date.from(datePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date sqlDate = new Date(date.getTime());

        DateFormat df = new SimpleDateFormat("dd/MM/yy");
        Date dateobj = new Date();

//        Guests guest = new Guests(0,fName,lName,num,phoneNum,sqlDate,dateobj);
        return null;
    }

    private  void printError(Exception ex){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(ex.toString());
        alert.showAndWait();
    }

    private void initDB() throws Exception {
        dbConnection db = new dbConnection();
        connection = db.getConnection();
    }

}
