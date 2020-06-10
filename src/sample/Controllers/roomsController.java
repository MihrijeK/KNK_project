package Controllers;

public class roomsController implements Initializable {
      @FXML private DatePicker firstDatePickerField;
      @FXML private DatePicker lastDatePickerField;
      @FXML private Button findButtonId;
      @FXML private TableView<Rooms> tableView;
      @FXML private TableColumn<Rooms,Integer> roomNumberCol;
      @FXML private TableColumn<Rooms,Integer> roomFloorCol;
      @FXML private TableColumn<Rooms,Integer> capacityCol;
      @FXML private TableColumn<Rooms,Integer> bedsCol;
      @FXML private TableColumn<Rooms,String> roomTypeCol;
      @FXML private TableColumn<Rooms,Integer> priceCol;
      @FXML private TableColumn<Rooms,CheckBox> reservationCol;
      @FXML private Button makeReservation;
      
      dbConnection connectionClass = new dbConnection();
      Connection connection;

      ObservableList<Rooms> roomList=FXCollections.observableArrayList();
      
      @Override
      public void initialize(URL url, ResourceBundle resourceBundle) {

      }
      
      public void findButtonClicked(ActionEvent actionEvent) {
        String newFirstDate=firstDatePickerField.getValue().toString();
        String newLastDate=lastDatePickerField.getValue().toString();

        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        Date firstDate=format.parse(newFirstDate);
        Date lastDate=format.parse(newLastDate);
      }
      
      public void onMakeReservationButtonClicked(ActionEvent actionEvent){
        
      }
      
      public void onCancelButtonClicked(ActionEvent actionEvent){
        TableView.TableViewSelectionModel<Rooms> selectionModel=tableView.getSelectionModel();
        selectionModel.clearSelection();
      }
      
      private void setDefaultDate(){
        Instant week=Instant.now().plus(7,ChronoUnit.DAYS);
        LocalDate currentLocalDate=LocalDate.now();
        LocalDate localDateFromAWeek=LocalDate.ofInstant(week, ZoneOffset.UTC);
        firstDatePickerField.setValue(currentLocalDate);
        lastDatePickerField.setValue(localDateFromAWeek);
    }
    }
