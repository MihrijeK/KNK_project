package sample.Controllers.Admin.StaffControllers;

import Helpers.Staff;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Components.ErrorPopupComponent;
import sample.Controllers.AdminDashboard;
import sample.Controllers.Partials.UserCardController;
import sample.Models.StaffRoleModel;
import sample.Repositories.StaffRepository;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class CreateStaffMemberView {
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
    private ArrayList<StaffRoleModel> roleList = new ArrayList<>(Arrays.asList(new StaffRoleModel("Manager",1550.2),
            new StaffRoleModel("Waiter",568.24),new StaffRoleModel("Recepsionist",750.5)));


    public CreateStaffMemberView() throws Exception {
        stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(AdminDashboard.class.getResource("../Views/AdminViews/StaffViews/CreateStaffMemberView.fxml"));
            loader.setController(this);

            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Create staff member");
            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void display(ObservableList<Staff> staffi) throws IOException, SQLException {
        createNewStaffMember.setOnAction(e -> {
            try {
                createButton(createStaffObj(first_name, last_name, personal_number, phone_number, salary, password, birthday,position,Male,Female),staffi);
                System.out.println(position.getValue());
                salary.setText(getSalary((String) position.getValue()));
                System.out.println(salary.getText());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        cancleCreation.setOnAction(e -> stage.close());
        position.getItems().addAll("Manager","Waiter","Recepsionist");
        stage.showAndWait();
    }

    public Staff createStaffObj(TextField firstname, TextField lastname, TextField personalnumber, TextField phonenumber, Label salary, PasswordField passwordd,
                                DatePicker bday,ChoiceBox position,RadioButton male, RadioButton female) throws Exception {
        Staff staff = null;
        if (firstname.getText().trim().isEmpty() || lastname.getText().trim().isEmpty() || personalnumber.getText().trim().isEmpty() || phonenumber.getText().trim().isEmpty() ||
                passwordd.getText().trim().isEmpty()) {
            ErrorPopupComponent.show("EmptyField","Error");
        } else {
            String fName = firstname.getText();
            String lName = lastname.getText();
            String persNumber = personalnumber.getText();
            int prs = Integer.parseInt(persNumber);
            String phnNumber = phonenumber.getText();
            double sal = Double.parseDouble(getSalary(position.getValue().toString()));
            String psw = passwordd.getText();
            Date dt = getDate(bday);
            String positionn = position.getValue().toString();
            String gendeer = "";

            if(male.isSelected()){
                gendeer = "Male";
            }else if (female.isSelected())
                gendeer = "Female";
            else
                gendeer = null;

            staff = new Staff(StaffRepository.getLastID()+1, fName, lName, prs, phnNumber, gendeer, dt, positionn, sal, psw);
        }
        return staff;
    }

    public void createButton(Staff staff,ObservableList<Staff> staffi) throws Exception {
        if (staff != null) {
            UserCardController us = new UserCardController();

            if(us.display(staff,staffi)){
                StaffRepository.insert(staff);
                System.out.println("Done");
                first_name.clear();
                last_name.clear();
                personal_number.clear();
                phone_number.clear();
                password.clear();
                salary.setText("0");
                birthday.setValue(null);
                Male.setSelected(false);
                Female.setSelected(false);
                position.setValue(null);
            }

        }
    }

    public String getSalary(String position) throws SQLException {
        double salary = 0;
        for (StaffRoleModel stf : roleList) {
            if(stf.getPosition().equals(position)){
                salary = stf.getSalary();
            }
        }
        return Double.toString(salary);
    }

    public Date getDate(DatePicker dt) {
        LocalDate localDate = dt.getValue();
        Date date = java.sql.Date.valueOf(localDate);
        return date;
    }
}
