package sample.Controllers;

public class MainManagingController implements Initializable {
    @FXML private Button mainBtn;
    @FXML private Button reservationsBtn;
    @FXML private Button paymentsBtn;
    @FXML private Button servicesBtn;
    @FXML private Pane mainPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            viewLoader("Reservations");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
