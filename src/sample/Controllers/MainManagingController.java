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

    @FXML
    private void onButtonClicked(ActionEvent actionEvent){
        try{
            if(actionEvent.getSource()==mainBtn){
                viewLoader("Main");
            }
            else if(actionEvent.getSource()==reservationsBtn){
                viewLoader("Reservations");
            }
        }
    }
}
