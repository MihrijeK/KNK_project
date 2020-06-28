package sample.Controllers.Admin.StaffControllers;

import Helpers.Staff;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import DatabaseConnection.*;

public class EditStaffMemberView {

    private Staff staff;
    @FXML
    private TextField first_name;
    @FXML
    private TextField last_name;
    @FXML
    private TextField personal_number;
    @FXML
    private TextField phone_number;
    @FXML
    private DatePicker birthdate;
    @FXML
    private ChoiceBox position;
    @FXML
    private Label salary;
    @FXML
    private PasswordField password;
    @FXML
    private Button editStaffMemberBtn;
    @FXML
    private Button cancleCreation;

    private Connection connection = dbConnection.getConnection();

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    private final Stage stage;
    public EditStaffMemberView(Staff staff) throws Exception {
        this.staff = staff;
        stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/EditStaffMemberView.fxml"));
            loader.setController(this);

            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Edit staff member");
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void display()  {
        first_name.setText(staff.getFirstName());
        last_name.setText(staff.getLastName());
        personal_number.setText(Integer.toString(staff.getPersonalNumber()));
        phone_number.setText(staff.getPhoneNumber());
        salary.setText(Double.toString(staff.getSalary()));
        password.setText(staff.getPassword());
        editStaffMemberBtn.setOnAction(e->{
            try {
                updateStaffMember(staff.getId(),first_name.getText(),last_name.getText(),Integer.parseInt(personal_number.getText()),phone_number.getText(),500,password.getText());
                stage.close();
                System.out.println("Updated");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        cancleCreation.setOnAction(e->stage.close());
        stage.showAndWait();
    }

    public void updateStaffMember(int id,String fname,String lname,int prs_num,String phnNumber,int salary,String passwordd) throws Exception {
        String query = "UPDATE staff SET first_name = '"+fname+"' ,last_name = '"+lname+"',personal_number = "+prs_num+",position = 'TEST',birthdate = '2020-05-01'," +
                "phone_number = '"+phnNumber+"',salary = "+salary+",passwordd = '"+passwordd+"' WHERE id = "+id+";";

        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
    }
}
