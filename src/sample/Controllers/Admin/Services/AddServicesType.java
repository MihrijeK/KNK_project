package sample.Controllers.Admin.Services;

import Helpers.Service_Type;
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
import sample.Repositories.ServicesTypeRepository;

import java.io.IOException;

public class AddServicesType {

    private final Stage stage;
    @FXML private TextField serviceName;
    @FXML private TextField servicePrice;
    @FXML private TextField serviceQuantity;
    @FXML private Button addNewServiceType;
    @FXML private Button cancleButton;

    public AddServicesType(){
        stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(AdminDashboard.class.getResource("../Views/AdminViews/Services/AddServicesType.fxml"));
            loader.setController(this);

            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Add servics");
            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void display(ObservableList<Service_Type> services){
        System.out.println(serviceName.getText());
        addNewServiceType.setOnAction(e -> {
            try {
                createButton(createServicesObj(serviceName, servicePrice, serviceQuantity),services);
                System.out.println("DONE");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        cancleButton.setOnAction(e -> stage.close());
        stage.showAndWait();
    }

    public Service_Type createServicesObj(TextField serviceName, TextField servicePrice, TextField serviceQuantity) throws Exception {
        String sName = serviceName.getText();
        double sPrice = Double.parseDouble(servicePrice.getText());
        int sQuantity = Integer.parseInt(serviceQuantity.getText());
        Service_Type rms = new Service_Type(ServicesTypeRepository.getLastID()+1,sName, sPrice, sQuantity);
        return rms;
    }

    public void createButton(Service_Type service,ObservableList<Service_Type> services) throws Exception {
        ServicesTypeRepository.insert(service);
        services.addAll(service);
        System.out.println("Done");

        serviceName.clear();
        servicePrice.clear();
        serviceQuantity.clear();
    }
}
