public class ReservationsController implements Initializable {
    private LocalDate checkin_date;
    private LocalDate checkout_date;
    private ObservableList<Rooms> roomsToBook;
    private double total=0;
    private int guestId=0;
    private long days;
    
    ReservationsRepository reservationsRepository=new ReservationsRepository();
    ObservableList<String> roomTypes= FXCollections.observableArrayList();
}
