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
        TableView.TableViewSelectionModel<Rooms> selectionModel=tableView.getSelectionModel();
        ObservableList<Rooms> roomsToBook = selectionModel.getSelectedItems();

        if(roomsToBook.isEmpty()){
            Alert noRoomsSelected=new Alert(Alert.AlertType.INFORMATION);
            noRoomsSelected.setContentText("No rooms selected!\nSelect a room to make a reservation.");
            noRoomsSelected.showAndWait();
        }else{
            try {
                URL url = new File("src/views/reservation.fxml").toURI().toURL();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(url);
                Pane newScreen = loader.load();
                ReservationsController reservationsController=loader.getController();
                reservationsController.getRooms(roomsToBook,firstDatePickerField.getValue().toString(),lastDatePickerField.getValue().toString());
      
                Scene scene=new Scene(newScreen);
                Stage stage=(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
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
            
        private void loadAvailableRooms(String firstDate,String lastDate,Connection connection) throws Exception{
              roomList.clear();
              String query="select * \n" +
                      "from rooms r\n" +
                      "where r.room_number not in(select r.room_number \n" +
                      "from reservations res inner join rooms r on res.room_id=r.room_number\n" +
                      "where checkin_date='"+firstDate+"' or checkout_date='"+lastDate+"')";

              Statement stmt=connection.createStatement();
              ResultSet rs=stmt.executeQuery(query);
              
              while(rs.next()){
                  roomList.add(new Rooms(rs.getInt("room_number"),rs.getInt("floor_number"),rs.getInt("capacity"),
                    rs.getInt("bed_number"),rs.getString("room_type"),rs.getDouble("price")));
              }
              
              tableView.setPlaceholder(new Label("No rooms available"));
              tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
              onClickSelect();
        }
    }
