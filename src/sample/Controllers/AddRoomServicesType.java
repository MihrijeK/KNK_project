package sample.Controllers;

        import DatabaseConnection.dbConnection;
        import Helpers.Service_Type;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.*;
        import javafx.stage.Modality;
        import javafx.stage.Stage;

        import java.io.IOException;
        import java.sql.Connection;
        import java.sql.SQLException;
        import java.sql.Statement;

public class AddRoomServicesType{

        private Connection connection = dbConnection.getConnection();
    private Stage stage;
    @FXML private TextField Price;
    @FXML private TextField serviceName;
    @FXML private TextField quantity;
    @FXML private Button addNewServiceType;
    @FXML private Button cancleButton;
        
    public void AddServicesType() throws Exception {
        stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../sample.Views/AddServicesType.fxml"));
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

    public AddRoomServicesType() throws Exception {
    }

    public void display() throws IOException, SQLException {
        addNewServiceType.setOnAction(e-> {
            try {
                createButton(createServiceTypeObj(serviceName,Price,quantity));
                System.out.println("DONE");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        cancleButton.setOnAction(e->stage.close());
        stage.showAndWait();
    }
        
       public Service_Type createServiceTypeObj(TextField Servicename, TextField price, TextField quantity){
        String Service_name = Servicename.getText();
        double Price = Double.parseDouble(price.getText());
        int qty = Integer.parseInt(quantity.getText());

        Service_Type stype = new Service_Type(1,Service_name,Price,qty);
        return stype;
      }
        
      public String serviceTypeQuery(Service_Type St) throws Exception {
        StringBuilder query = new StringBuilder();

        query.append("INSERT INTO services_type(service_name,price,quantity) VALUES(");
        query.append("'"+St.getService_name()+"'");
        query.append(", "+St.getPrice()+"'");
        query.append(", "+St.getQuantity()+"'");

        return query.toString();
    }
  
        
      public void createButton(Service_Type St) throws Exception {
        Statement statement = connection.createStatement();
        int affectedRows = statement.executeUpdate(serviceTypeQuery(St),Statement.RETURN_GENERATED_KEYS);
        if(affectedRows<=0) throw new Exception("Failed");
        System.out.println("Done");
        serviceName.clear();
        Price.clear();
        quantity.clear();
    }
        
}
