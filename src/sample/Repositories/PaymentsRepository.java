package sample.Repositories;

import DatabaseConnection.dbConnection;
import Helpers.Rooms;
import Helpers.Service_Type;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import sample.Models.View.PaymentModel;

import java.awt.*;
import javafx.scene.control.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PaymentsRepository {
    private static Connection connection;
    private static dbConnection dbconnection = new dbConnection();

    private static PaymentModel parseFromRes(ResultSet res) throws Exception {
        int payment_id = res.getInt("payment_id");
        String firstname = res.getString("first_name");
        String lastname = res.getString("last_name");
        Date date = res.getDate("checkin_date");
        double price = res.getDouble("price");
        int is_payed = res.getInt("is_payed");
        String isPayed = null;
            if (is_payed==0){
                isPayed="Jo e paguar";
            }
            else if (is_payed==1){
            isPayed="E paguar";
            }

        return new PaymentModel(payment_id, firstname, lastname, date, price, isPayed);
    }

        public static List<PaymentModel> selectAll() throws Exception {
        ArrayList<PaymentModel> pModel = new ArrayList<>();
        connection=dbconnection.getConnection();

        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM " +
                "((reservations INNER JOIN payments ON reservations.payment_id = payments.id) " +
                "INNER JOIN guests ON reservations.guest_id = guests.id) " +
                "WHERE checkin_date = CURRENT_DATE");
        ResultSet res = stmt.executeQuery();
        while (res.next()) {
            pModel.add(parseFromRes(res));
        }
        return pModel;
    }
       public static List<PaymentModel> selectAllByDate(Date date) throws Exception {
        Connection connection = dbConnection.getConnection();
        ArrayList<PaymentModel> pModel = new ArrayList<>();

        PreparedStatement stmt = connection.prepareStatement("SELECT r.payment_id,g.first_name,g.last_name,r.checkin_date,r.checkout_date,p.price,p.is_payed \n" +
                "FROM reservations r INNER JOIN payments p ON r.payment_id = p.id\n" +
                "INNER JOIN guests g ON r.guest_id = g.id \n" +
                "WHERE r.checkin_date = '"+ date +"';");
        ResultSet res = stmt.executeQuery();
        while (res.next()) {
            pModel.add(parseFromRes(res));
        }
        return pModel;
    }
    
    public static void guestInfo(int user, Label personalNr, Label emriMbiemri) throws Exception {
        connection=dbconnection.getConnection();
        ResultSet guestNameID = connection.createStatement().executeQuery("select first_name, last_name, personal_number from guests\n" +
                " where id="+user);
        while(guestNameID.next()){
            String rezultati = guestNameID.getString("first_name") + " " + guestNameID.getString("last_name");
            personalNr.setText(guestNameID.getString("personal_number"));
            emriMbiemri.setText(rezultati);
        }
    }
    
    public static void roomsBill(int user, ObservableList oblist, double total) throws Exception {
        connection=dbconnection.getConnection();
        ResultSet tabela = connection.createStatement().executeQuery("select dh.room_number, dh.room_type, dh.price from rooms dh \n" +
                "inner join reservations r on r.room_id=dh.room_number " +
                "inner join payments p on p.id = r.payment_id " +
                "where r.guest_id = "+user +" and p.is_payed = 0;");
        while(tabela.next()){
            oblist.add(new Rooms(tabela.getInt("room_number"),
                    tabela.getString("room_type"), tabela.getDouble("price")));
            total += tabela.getDouble("price");
        }
    }
    
    public static void servicesBill(int user, ObservableList oblist1, double total) throws Exception {
        connection=dbconnection.getConnection();
        ResultSet services = connection.createStatement().executeQuery("select st.service_name, st.price from services_type st \n" +
                "inner join services s on s.service_id = st.id " +
                "inner join payments p on p.id = s.payment_id " +
                "where s.guest_id = "+user+" and p.is_payed = 0;");
        while (services.next()){
            oblist1.add(new Service_Type(services.getString("service_name"), services.getDouble("price")));
            total += services.getDouble("price");
        }
    }
    
    public static void updatePayments(int user, String metodaEzgjedhur) throws Exception {
        String PaymentQuery = "update payments p \n" +
                "left join reservations r on r.payment_id = p.id \n" +
                "left join services s on s.payment_id = p.id \n" +
                "set p.payment_method = '"+metodaEzgjedhur+"', p.is_payed = 1, p.pay_date = now() \n" +
                "where p.guest_id="+user;
        Statement statement = connection.createStatement();
        statement.executeUpdate(PaymentQuery);
    }
}
