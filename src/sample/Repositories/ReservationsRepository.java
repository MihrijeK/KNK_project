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
  
}
