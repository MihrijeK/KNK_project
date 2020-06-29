package controllers;

import Helpers.Person;
import Helpers.Rooms;
import Repositories.ReservationsRepository;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.File;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

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
    List<Rooms> roomsSelected;
    ObservableList<Rooms> roomsToBook;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idField.setOnKeyPressed((event)->{
            if(event.getCode().equals(KeyCode.ENTER)){
                if(idField.getText().equals("")){
                    Alert alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("A personal number is mandatory!");
                    alert.showAndWait();
                }else{
                    try{
                        String ID=idField.getText();
                        Person person=reservationsRepository.getGuest(ID);

                        if(person==null){
                            FXMLLoader loader=new FXMLLoader();
                            URL url1 = new File("src/views/InsertGuest.fxml").toURI().toURL();
                            loader.setLocation(url1);
                            Pane pane=loader.load();
                            Scene scene=new Scene(pane);
                            Stage stage=new Stage();
                            stage.initModality(Modality.APPLICATION_MODAL);
                            stage.setScene(scene);
                            stage.show();
                            }

                    }catch(Exception e){
                        Alert alert=new Alert(Alert.AlertType.ERROR);
                        alert.setContentText(ex.getMessage());
                        alert.showAndWait();
                    }
    }

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
