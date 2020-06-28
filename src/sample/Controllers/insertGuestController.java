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
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        genderToggleGroup = new ToggleGroup();
        this.male.setToggleGroup(this.genderToggleGroup);
        this.female.setToggleGroup(this.genderToggleGroup);
    }
}
