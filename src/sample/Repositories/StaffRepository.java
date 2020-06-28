package sample.Repositories;

import sample.Models.Staff;
import sample.dbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StaffRepository {

    public static int count() throws Exception {
        Connection connection = dbConnection.getConnection();
        ResultSet resultSet = connection.prepareStatement("SELECT * FROM staff").executeQuery();
        resultSet.next();
        return resultSet.getInt(1);
    }

    public static List<Staff> selectAll() throws Exception {
        ArrayList<Staff> list = new ArrayList<>();
        Connection conn = dbConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM staff");
        ResultSet res = stmt.executeQuery();
        while (res.next()) {
            list.add(parseFromRes(res));
        }
        return list;
    }

    public static List<Staff> findPosition(String position) throws Exception {
        ArrayList<Staff> list = new ArrayList<>();
        Connection conn = dbConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM staff WHERE position = ?");
        stmt.setString(1, position);

        ResultSet res = stmt.executeQuery();
        while (res.next()) {
            list.add(parseFromRes(res));
        }
        return list;
    }

    public static Staff insert(Staff model) throws Exception {
        Connection connection = dbConnection.getConnection();

        PreparedStatement stmt = connection.prepareStatement("INSERT INTO staff (first_name, last_name, personal_number, position, birthdate, phone_number, salary, passwordd, gender) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");

        stmt.setString(1, model.getFirstName());
        stmt.setString(2, model.getLastName());
        stmt.setInt(3, model.getPersonalNumber());
        stmt.setString(4, model.getPosition());
        stmt.setString(5, model.getBirthdate().toString());
        stmt.setString(6, model.getPhoneNumber());
        stmt.setDouble(7, model.getSalary());
        stmt.setString(8, model.getPassword());
        stmt.setString(9, model.getGender());

        stmt.executeUpdate();

        ResultSet res = connection.prepareStatement("SELECT id FROM staff ORDER BY id DESC LIMIT 1").executeQuery();
        res.next();
        int id = res.getInt("id");

        return find(id);
    }

    public static Staff update(Staff model) throws Exception {
        Connection conn = dbConnection.getConnection();

        PreparedStatement stmt = conn
                .prepareStatement("UPDATE staff SET first_name = ?, last_name = ?, personal_number = ?, phone_number = ?, gender = ?, birthdate = ?, position = ?, salary = ?, passwordd = ?" +
                        " WHERE id = ?");

        stmt.setString(1, model.getFirstName());
        stmt.setString(2, model.getLastName());
        stmt.setInt(3, model.getPersonalNumber());
        stmt.setString(4, model.getPhoneNumber());
        stmt.setString(5, model.getGender());
        stmt.setDate(6, (java.sql.Date) model.getBirthdate());
        stmt.setString(7, model.getPosition());
        stmt.setDouble(8, model.getSalary());
        stmt.setString(9, model.getPassword());
        stmt.setInt(10, model.getId());

        stmt.executeUpdate();

        return find(model.getId());
    }

    public static Staff find(int id) throws Exception {
        Connection conn = dbConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM staff WHERE id = ?");
        stmt.setInt(1, id);
        ResultSet res = stmt.executeQuery();
        if (!res.next())
            return null;
        return parseFromRes(res);
    }

    private static Staff parseFromRes(ResultSet res) throws Exception {
        int id = res.getInt("id");
        String firstName = res.getString("first_name");
        String lastName = res.getString("last_name");
        int personalNumber = res.getInt("personal_number");
        String phoneNumber = res.getString("phone_number");
        String gender = res.getString("gender");
        Date birthdate = res.getDate("birthdate");
        String position = res.getString("position");
        double salary = res.getDouble("salary");
        String password = res.getString("passwordd");

        return new Staff(id, firstName, lastName, personalNumber, phoneNumber, gender, birthdate, position, salary, password);
    }

    public static boolean remove(int id) throws Exception {
        Connection conn = dbConnection.getConnection();

        PreparedStatement stmt = conn.prepareStatement("DELETE FROM staff WHERE id = ?");
        stmt.setInt(1, id);
        return stmt.executeUpdate() >= 1;
    }

    public static int getLastID() throws Exception {
        Connection conn = dbConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement("select max(id) from staff\n");
        ResultSet res = stmt.executeQuery();

        if (res.next()){
            return res.getInt("max(id)");
        }
        return 0;
    }
}
