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
}
