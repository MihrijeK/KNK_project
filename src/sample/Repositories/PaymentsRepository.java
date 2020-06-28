package sample.Repositories;

import DatabaseConnection.dbConnection;
import sample.Models.View.PaymentModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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


        return new PaymentModel(payment_id, firstname, lastname, date, price, is_payed);
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
    
    public static ResultSet guestInfo(int user) throws Exception {
        connection=dbconnection.getConnection();
        ResultSet nameAndID = connection.createStatement().executeQuery("select first_name, last_name, personal_number from guests\n" +
                " where id="+user);
        return nameAndID;
    }
    
    public static ResultSet roomsBill(int user) throws Exception {
        connection=dbconnection.getConnection();
        ResultSet tabela = connection.createStatement().executeQuery("select dh.room_number, dh.room_type, dh.price from rooms dh \n" +
                "inner join reservations r on r.room_id=dh.room_number " +
                "inner join payments p on p.id = r.payment_id " +
                "where r.guest_id = "+user +" and p.is_payed = 0;");
        return tabela;
    }
    
    public static ResultSet servicesBill(int user) throws Exception {
        connection=dbconnection.getConnection();
        ResultSet tabela = connection.createStatement().executeQuery("select st.service_name, st.price from services_type st \n" +
                "inner join services s on s.service_id = st.id " +
                "inner join payments p on p.id = s.payment_id " +
                "where s.guest_id = "+user+" and p.is_payed = 0;");
        return tabela;
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
