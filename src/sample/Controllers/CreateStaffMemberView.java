package sample.Controllers;

import DatabaseConnection.dbConnection;
import Helpers.Staff;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateStaffMemberView {
    private Connection connection = dbConnection.getConnection();
    @FXML private TextField first_name;
    @FXML private TextField last_name;
    @FXML private TextField personal_number;
    @FXML private TextField phone_number;
    @FXML private DatePicker birthday;
    @FXML private ChoiceBox position;
    @FXML private Label salary;
    @FXML private PasswordField password;
    @FXML private Button createNewStaffMember;
    @FXML private Button cancleCreation;
    @FXML private RadioButton Male;
    @FXML private RadioButton Female;

    private final Stage stage;

    public CreateStaffMemberView() throws Exception {
        stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/CreateStaffMemberView.fxml"));
            loader.setController(this);

            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Edit staff member");
            stage.setScene(scene);
            position.setItems(choiceBoxValues());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void display() throws IOException, SQLException {

        String finalGender= "Male";
        createNewStaffMember.setOnAction(e->{
            try {
                createButton(createStaffObj(first_name,last_name,personal_number,phone_number, finalGender,salary,password));
                System.out.println(position.getValue());
                salary.setText(setSalary((String) position.getValue()));
                System.out.println(salary.getText());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("User Created succesuffly");
                alert.showAndWait();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        cancleCreation.setOnAction(e-> stage.close());
        stage.showAndWait();
    }

    public Staff createStaffObj(TextField firstname,TextField lastname,TextField personalnumber,TextField phonenumber,String gender,Label salary,PasswordField passwordd){
        String fName = firstname.getText();
        String lName = lastname.getText();
        String persNumber = personalnumber.getText();
        int prs = Integer.parseInt(persNumber);
        String phnNumber = phonenumber.getText();
        String salaryy = salary.getText();
        double sal = Double.parseDouble(salary.getText());
        String psw = passwordd.getText();

        Staff staff = new Staff(1,fName,lName,prs,phnNumber,gender,java.util.Calendar.getInstance().getTime(),"Boss",sal,psw);
        return staff;
    }

    public String staffQuery(Staff stf) throws Exception {
        StringBuilder query = new StringBuilder();

        query.append("INSERT INTO staff(first_name,last_name,personal_number,position,birthdate,phone_number,salary,passwordd) VALUES(");
        query.append("'"+stf.getFirstName()+"'");
        query.append(", '"+stf.getLastName()+"'");
        query.append(", "+stf.getPersonalNumber());
        query.append(", '"+stf.getPosition()+"'");
        query.append(",'2019-05-01'");
        query.append(", '"+stf.getPhoneNumber()+"'");
        query.append(", "+stf.getSalary());
        query.append(", '"+stf.getPassword()+"');");

        return query.toString();
    }
}