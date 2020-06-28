package sample.Controllers.Admin.Services;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Controllers.AdminDashboard;
import sample.Models.Service_Type;
import sample.Repositories.ServicesTypeRepository;

import java.io.IOException;

public class EditServiceType {

    private final Stage stage;
    private Service_Type service_type;
    @FXML private TextField serviceName;
    @FXML private TextField servicePrice;
    @FXML private TextField serviceQuantity;
    @FXML private Button editServiceType;
    @FXML private Button cancleButton;

    public EditServiceType(Service_Type sType){
        this.service_type = sType;
        stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(AdminDashboard.class.getResource("../Views/AdminViews/Services/EditServicesType.fxml"));
            loader.setController(this);

            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Edit servics");
            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void display(ObservableList<Service_Type> services){
        serviceName.setText(service_type.getService_name());
        servicePrice.setText(Double.toString(service_type.getPrice()));
        serviceQuantity.setText(Integer.toString(service_type.getQuantity()));

        editServiceType.setOnAction(e -> {
            Service_Type service = new Service_Type(service_type.getId(),serviceName.getText(),Double.parseDouble(servicePrice.getText()),Integer.parseInt(serviceQuantity.getText()));
            services.addAll(service);
            try {
                ServicesTypeRepository.update(service);
            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("Updated");
                stage.close();
            }
        });
        cancleButton.setOnAction(e -> stage.close());
        stage.showAndWait();
    }


}
