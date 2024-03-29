package sample.Models.View;

import Helpers.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Repositories.PaymentsRepository;
import sample.Repositories.RoomRespository;
import sample.Repositories.ServicesTypeRepository;
import sample.Repositories.StaffRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class TableViewContent {

    public static ObservableList<Staff> setSaff(TableColumn<Staff, Integer> col_id, TableColumn<Staff, String> col_fname, TableColumn<Staff, String> col_lname, TableColumn<Staff, Integer> col_prsNum, TableColumn<Staff,
            String> col_position, TableColumn<Staff, Date> col_bday, TableColumn<Staff, String> col_phone, TableColumn<Staff, Integer> col_salary, TableColumn<Staff, String> col_gender, ChoiceBox<String> positionCB) {

        ObservableList<Staff> staffObservableList = FXCollections.observableArrayList();
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_fname.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        col_lname.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        col_prsNum.setCellValueFactory(new PropertyValueFactory<>("personalNumber"));
        col_position.setCellValueFactory(new PropertyValueFactory<>("position"));
        col_bday.setCellValueFactory(new PropertyValueFactory<>("birthdate"));
        col_phone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        col_salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        List<Staff> staffList = null;
        try {
            if(positionCB.getValue().equals("All")){
                staffList = StaffRepository.selectAll();
            }else
                staffList = StaffRepository.findPosition(positionCB.getValue());

            for (Staff sf : staffList) {
                staffObservableList.add(sf);
            }
            return staffObservableList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ObservableList<Rooms> setRooms(TableColumn<Rooms, Integer> roomNumber, TableColumn<Rooms, Integer> floorNumber, TableColumn<Rooms, Integer> capacity, TableColumn<Rooms, Integer> bedNumber,
                                                 TableColumn<Rooms, String> roomType, TableColumn<Rooms, Double> price) {
        ObservableList<Rooms> roomsObservableList = FXCollections.observableArrayList();
        roomNumber.setCellValueFactory(new PropertyValueFactory<>("room_number"));
        floorNumber.setCellValueFactory(new PropertyValueFactory<>("floor_number"));
        capacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        bedNumber.setCellValueFactory(new PropertyValueFactory<>("bed_number"));
        roomType.setCellValueFactory(new PropertyValueFactory<>("room_type"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        try {
            List<Rooms> roomsList = RoomRespository.selectAll();
            for (Rooms rm : roomsList) {
                roomsObservableList.add(rm);
            }
            return roomsObservableList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ObservableList<Service_Type> setServices(TableColumn<Service_Type, Integer> service_id, TableColumn<Service_Type, String> service_name, TableColumn<Service_Type, Double> service_price,
                                                           TableColumn<Service_Type, Integer> service_quantity) {
        ObservableList<Service_Type> servicesObservableList = FXCollections.observableArrayList();
        service_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        service_name.setCellValueFactory(new PropertyValueFactory<>("service_name"));
        service_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        service_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        try {
            List<Service_Type> serviceList = ServicesTypeRepository.selectAll();
            for (Service_Type rm : serviceList) {
                servicesObservableList.add(rm);
            }
            return servicesObservableList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ObservableList<Rooms> setRoomsByType(TableColumn<Rooms, Integer> roomNumber, TableColumn<Rooms, Integer> floorNumber, TableColumn<Rooms, Integer> capacity, TableColumn<Rooms, Integer> bedNumber,
                                                 TableColumn<Rooms, String> roomType, TableColumn<Rooms, Double> price,ChoiceBox<String> roomTypeFilter,ChoiceBox<String> bedNumberFilter,ChoiceBox<String> roomCapacityFilter) {
        ObservableList<Rooms> roomsObservableList = FXCollections.observableArrayList();
        roomNumber.setCellValueFactory(new PropertyValueFactory<>("room_number"));
        floorNumber.setCellValueFactory(new PropertyValueFactory<>("floor_number"));
        capacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        bedNumber.setCellValueFactory(new PropertyValueFactory<>("bed_number"));
        roomType.setCellValueFactory(new PropertyValueFactory<>("room_type"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        try {
            List<Rooms> roomsList = RoomRespository.selectAllRoomsByFilter(roomTypeFilter.getValue(),bedNumberFilter.getValue(),roomCapacityFilter.getValue());
            for (Rooms rm : roomsList) {
                roomsObservableList.add(rm);
            }
            return roomsObservableList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static ObservableList<PaymentModel> setPayments(TableColumn<PaymentModel,Integer> payment_id, TableColumn<PaymentModel,String> firstname,
                                                           TableColumn<PaymentModel,String> lastname, TableColumn<PaymentModel,Date> date,
                                                           TableColumn<PaymentModel,Double> price , TableColumn<PaymentModel, Integer> isPayed, DatePicker datePicker) {

        ObservableList<PaymentModel> staffObservableList = FXCollections.observableArrayList();
        payment_id.setCellValueFactory(new PropertyValueFactory<>("payment_id"));
        firstname.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        lastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
       isPayed.setCellValueFactory(new PropertyValueFactory<>("isPayed"));

        try {
            List<PaymentModel> payments = null;
            if(datePicker.getValue()==null){
                payments = PaymentsRepository.selectAll();
            }else
                payments = PaymentsRepository.selectAllByDate(getDate(datePicker));
            for (PaymentModel sf : payments) {
                staffObservableList.add(sf);
            }

            return staffObservableList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date getDate(DatePicker dt) {
        LocalDate localDate = dt.getValue();
        Date date = java.sql.Date.valueOf(localDate);
        return date;
    }
}

