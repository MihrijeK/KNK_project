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
            else if(actionEvent.getSource()==paymentsBtn){
                viewLoader("Payments");
            }
            else if(actionEvent.getSource()==servicesBtn){
                viewLoader("Services");
            }lse{
                //do smth
            }
        }catch(Exception e){
            Alert alertBox=new Alert(Alert.AlertType.ERROR);
            alertBox.setContentText(e.getMessage());
            alertBox.showAndWait();
        }
        }
    }

    public void viewLoader(String view) throws Exception{
        FXMLLoader loader=new FXMLLoader();
        Parent node=null;
        switch(view) {
            case "Main":
                //dritarja main e mires
                break;
        }
    }
}
