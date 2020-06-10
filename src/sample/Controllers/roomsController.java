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
    }
