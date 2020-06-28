package sample.Controllers.Partials;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Controllers.AdminDashboard;
import sample.Models.Staff;

import java.io.IOException;

public class UserCardController {
    @FXML private Label fnameLabel;
    @FXML private Label lnameLabel;
    @FXML private Label personalNumLabel;
    @FXML private Label phoneNumLabel;
    @FXML private Label bdayLabel;
    @FXML private Label genderLabel;
    @FXML private Label positionLabel;
    @FXML private Label salaryLabel;
    @FXML private Button doneBtn;
    @FXML private Button cancleBtn;

    private final Stage stage;
    public boolean isSaved;

    public UserCardController() {
        stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(AdminDashboard.class.getResource("../Views/AdminViews/StaffViews/UserCard.fxml"));
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

    public boolean display(Staff staff, ObservableList<Staff> staffi) throws Exception {
        fnameLabel.setText(staff.getFirstName());
        lnameLabel.setText(staff.getLastName());
        personalNumLabel.setText(Integer.toString(staff.getPersonalNumber()));
        phoneNumLabel.setText(staff.getPhoneNumber());
        genderLabel.setText(staff.getGender());
        positionLabel.setText(staff.getPosition());
        salaryLabel.setText(Double.toString(staff.getSalary()));
        doneBtn.setOnAction(e -> {
            if(staffi != null){
                staffi.addAll(staff);
            }
            stage.close();
            isSaved = true;
        });
        cancleBtn.setOnAction(e->{
            stage.close();
            isSaved = false;
        });
        stage.showAndWait();
        return isSaved;
    }
}
