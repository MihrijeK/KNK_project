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
        Connection con = dbConnection.getConnection();

        PreparedStatement stmt = con.prepareStatement("SELECT * FROM " +
                "((reservations INNER JOIN payments ON reservations.payment_id = payments.id) " +
                "INNER JOIN guests ON reservations.guest_id = guests.id) " +
                "WHERE checkin_date = CURRENT_DATE");
        ResultSet res = stmt.executeQuery();
        while (res.next()) {
            pModel.add(parseFromRes(res));
        }
        return pModel;
    }
}
