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
         try {
            connection= connectionClass.getConnection();

            setDefaultDate();
            String defaultFirstDate=firstDatePickerField.getValue().toString();
            String defaultLastDate=lastDatePickerField.getValue().toString();
            loadAvailableRooms(defaultFirstDate,defaultLastDate,connection);

            }catch(Exception e){}
      }
      
      public void findButtonClicked(ActionEvent actionEvent) {
        try {
            String newFirstDate=firstDatePickerField.getValue().toString();
            String newLastDate=lastDatePickerField.getValue().toString();

            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
            Date firstDate=format.parse(newFirstDate);
            Date lastDate=format.parse(newLastDate);

            if(firstDate.compareTo(lastDate)<0){
                loadAvailableRooms(newFirstDate,newLastDate,connection);
            }else{
                Alert alertBox=new Alert(Alert.AlertType.INFORMATION);
                alertBox.setContentText("Dates you entered are not valid!");
                alertBox.showAndWait();
            }

        }catch (Exception e){

        }
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
