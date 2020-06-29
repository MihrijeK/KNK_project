public class ReservationsController implements Initializable {
   private LocalDate checkin_date;
   private LocalDate checkout_date;
   private ObservableList<Rooms> roomsToBook;
   private double total=0;
   private int guestId=0;
   private long days;

   @FXML private ScrollPane roomsPane;
   @FXML private VBox verticalBox;
   @FXML private ListView<String> roomsList;
   @FXML private ScrollPane bookedRoomsScroll;
   @FXML private TextField idField;
   @FXML private Label firstName;
   @FXML private Label lastName;
   @FXML private Label totalField;

   ReservationsRepository reservationsRepository=new ReservationsRepository();
   ObservableList<String> roomTypes= FXCollections.observableArrayList();

   public void getRooms(ObservableList<Rooms> rooms, LocalDate checkin_date, LocalDate checkout_date){
       this.checkin_date=checkin_date;
       this.checkout_date=checkout_date;
       this.roomsToBook=rooms;
       this.days=getDaysOfStaying(checkin_date,checkout_date);

       printRoomsSelected();
       for(Rooms selectedRooms:roomsToBook){
           try{
               double price=selectedRooms.getPrice()*days;
               total+=price;
               roomTypes.add(selectedRooms.getRoom_type()+" "+price);
           }catch(Exception e){
               System.out.println(e.getMessage());
           }

       }
   }
}
