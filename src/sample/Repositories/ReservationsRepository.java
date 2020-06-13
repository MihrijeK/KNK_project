package Repositories;


import Connectivity.dbConnection;
import Helpers.Person;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ReservationsRepository {
    dbConnection dbconnection=new dbConnection();
    Connection connection;
    public ReservationsRepository(){

    }
    
    public Person getGuest(String id) throws Exception{
            connection=dbconnection.getConnection();
            String findGuestQuery="select * from guests where personal_number=? limit 1";
            PreparedStatement prep=connection.prepareStatement(findGuestQuery);
            prep.setString(1,id);
            ResultSet rs=prep.executeQuery();

            if(rs.next()){
                Person person= new Person(rs.getInt(1),rs.getString(2),rs.getString(3),
                        rs.getLong(4),rs.getString(6),rs.getString(8),
                        rs.getDate(5));
                return person;
            }else return null;

    }
    
    public int getPaymentId() throws Exception{
        connection=dbconnection.getConnection();
        Statement stmt=connection.createStatement();
        ResultSet rs=stmt.executeQuery("select id from payments order by id desc limit 1");

        rs.next();
        return rs.getInt(1);
    }
    
    public int createPayment(String guestId,String price) throws Exception{
        String insertQuery="insert into payments(guest_id,price,payment_method) values(?,?,'cash')";
        PreparedStatement prep=connection.prepareStatement(insertQuery);
        prep.setString(1,guestId);//Kqyri edhe niher se jane si ints mundet me dal problem
        prep.setString(2,price);
        prep.executeUpdate();

        int paymentId=getPaymentId();
        return paymentId;
    }
    
    public void createReservation(int guest_id,int room_id,String checkin_date,String checkout_date,int paymentId) throws Exception{
        connection=dbconnection.getConnection();
        String createReservationQuery="insert into reservations(guest_id,room_id,checkin_date,checkout_date,payment_id) values(?,?,?,?,?)";
        PreparedStatement prep=connection.prepareStatement(createReservationQuery);
        
    }
  
}
