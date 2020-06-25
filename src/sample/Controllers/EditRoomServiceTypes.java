package sample.Controllers;

import  DatabaseConnection.dbConnection;
import Helpers.Service_Type;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class EditRoomServiceTypes {
  
    private Connection connection = dbConnection.getConnection();
  
    private final Stage stage;
  
    private Service_Type serviceType;
  
    @FXML private TextField price;
    @FXML private TextField serviceName;
    @FXML private TextField quantity;
    @FXML private Button updateButton;
    @FXML private Button cancleButton;
  
  public EditRoomServiceTypes(Service_Type serviceType) throws Exception {
        this.serviceType=serviceType;
        stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.Views.EditRoomServiceTypes.fxml"));
            loader.setController(this);

            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Edit Service Type");
            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
  
  public void display() throws IOException, SQLException {
        serviceName.setText(serviceType.getService_name());
        price.setText(Double.toString(serviceType.getPrice()));
        quantity.setText(Integer.toString(serviceType.getQuantity()));

        updateButton.setOnAction(e->{
            try {

               updateButton( serviceName.getId(),serviceName.getText(),Double.parseDouble(price.getText()),Integer.parseInt(quantity.getText()));
                System.out.println("Updated");
                stage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        cancleButton.setOnAction(e->stage.close());
        stage.showAndWait();
    }
  
  public void updateButton(String id, String service_name, double price, int quantity) throws Exception {
        String query = "UPDATE services_type SET service_name = '"+service_name+"', price = "+ price + ",quantity = "+quantity+" WHERE id = "+id+";";

        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
    }
  
}
