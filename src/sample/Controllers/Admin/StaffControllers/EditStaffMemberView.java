package sample.Controllers.Admin.StaffControllers;

import Helpers.SecurityHelper;
import Helpers.Staff;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Controllers.AdminDashboard;
import sample.Controllers.Partials.UserCardController;
import sample.Models.StaffRoleModel;
import sample.Repositories.StaffRepository;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class EditStaffMemberView {

    private Staff staff;
    @FXML private TextField first_name;
    @FXML private TextField last_name;
    @FXML private TextField personal_number;
    @FXML private TextField phone_number;
    @FXML private DatePicker birthdate;
    @FXML private ChoiceBox position;
    @FXML private Label salary;
    @FXML private PasswordField password;
    @FXML private Button editStaffMemberBtn;
    @FXML private Button cancleCreation;
    @FXML private RadioButton Male;
    @FXML private RadioButton Female;

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    private final Stage stage;

    private ArrayList<StaffRoleModel> roleList = new ArrayList<>(Arrays.asList(new StaffRoleModel("Manager",1550.2),
            new StaffRoleModel("Waiter",568.24),new StaffRoleModel("Recepsionist",750.5)));


    public EditStaffMemberView(Staff staff) throws Exception {
        this.staff = staff;
        stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(AdminDashboard.class.getResource("../Views/AdminViews/StaffViews/EditStaffMemberView.fxml"));
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

    public void display(ObservableList<Staff> staffi) {
        first_name.setText(staff.getFirstName());
        last_name.setText(staff.getLastName());
        personal_number.setText(Integer.toString(staff.getPersonalNumber()));
        phone_number.setText(staff.getPhoneNumber());
        salary.setText(Double.toString(staff.getSalary()));
        password.setText(staff.getPassword());
        birthdate.setValue(setDate(staff.getBirthdate()));
        position.getItems().addAll("Manager","Waiter","Recepsionist");
        position.setValue(staff.getPosition());
        salary.setText(Double.toString(staff.getSalary()));


        SecurityHelper sc = new SecurityHelper();
        if(staff.getGender().equals("Male")){
            Male.setSelected(true);
        }else if(staff.getGender().equals("Female")){
            Female.setSelected(true);
        }

        editStaffMemberBtn.setOnAction(e -> {
            try {
                String gendeer = "";
                if(Male.isSelected()){
                    gendeer = "Male";
                }else if (Female.isSelected())
                    gendeer = "Female";
                else
                    gendeer = null;
                Staff model = new Staff(staff.getId(),first_name.getText(),last_name.getText(),Integer.parseInt(personal_number.getText()),phone_number.getText()
                        ,gendeer,getDate(birthdate),position.getValue().toString(), Double.parseDouble(getSalary(position.getValue().toString())), sc.hashPassword(password.getText()));
                UserCardController us = new UserCardController();
                if(us.display(model,null)){
                    staffi.addAll(model);
                    StaffRepository.update(model);
                    stage.close();
                    System.out.println("Updated");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        cancleCreation.setOnAction(e -> stage.close());
        stage.showAndWait();
    }

    public Date getDate(DatePicker dt) {
        LocalDate localDate = dt.getValue();
        Date date = java.sql.Date.valueOf(localDate);
        return date;
    }

    public LocalDate setDate(Date date){
        Instant instant = Instant.ofEpochMilli(date.getTime());
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        LocalDate localDate = localDateTime.toLocalDate();
        return localDate;
    }

    public String getSalary(String position){
        double salary = 0;
        for (StaffRoleModel stf : roleList) {
            if(stf.getPosition().equals(position)){
                salary = stf.getSalary();
            }
        }
        return Double.toString(salary);
    }
}
