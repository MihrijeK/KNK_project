package sample.Repositories;


import DatabaseConnection.dbConnection;
import Helpers.Rooms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RoomRespository {

    public static List<Rooms> selectAll() throws Exception {
        ArrayList<Rooms> list = new ArrayList<>();
        Connection conn = dbConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM rooms");
        ResultSet res = stmt.executeQuery();
        while (res.next()) {
            list.add(parseFromRes(res));
        }
        return list;
    }

    public static List<Rooms> selectAllRoomsByFilter(String type,String bedNr,String capacity) throws Exception {
        ArrayList<Rooms> list = new ArrayList<>();
        Connection conn = dbConnection.getConnection();

        StringBuilder sb = new StringBuilder();
        boolean hasWhere = false;
        sb.append("SELECT * FROM rooms ");

        if (!type.equals("All")){
            sb.append(hasWhere ? " AND " : " WHERE ");
            sb.append("room_type = '" + type + "'");
            hasWhere = true;
        }
        if (!bedNr.equals("All")) {
            sb.append(hasWhere ? " AND " : " WHERE ");
            sb.append("bed_number = " + Integer.parseInt(bedNr) + "");
            hasWhere = true;
        }
        if (!capacity.equals("All")) {
            sb.append(hasWhere ? " AND " : " WHERE ");
            sb.append("capacity = " + Integer.parseInt(capacity) + "");
            hasWhere = true;
        }

        Statement stmt = conn.createStatement();
        ResultSet res = stmt.executeQuery(sb.toString());
        while (res.next()) {
            list.add(parseFromRes(res));
        }
        return list;
    }

    public static Rooms find(int id) throws Exception {
        Connection conn = dbConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM rooms WHERE room_number = ?");
        stmt.setInt(1, id);
        ResultSet res = stmt.executeQuery();
        if (!res.next())
            return null;
        return parseFromRes(res);
    }

    public static Rooms insert(Rooms model) throws Exception {
        Connection conn = dbConnection.getConnection();

        PreparedStatement stmt = conn
                .prepareStatement("INSERT INTO rooms (room_number, floor_number, capacity, bed_number, room_type, price) VALUES (?, ?, ?, ?, ?, ?)");
        stmt.setInt(1, model.getRoom_number());
        stmt.setInt(2, model.getFloor_number());
        stmt.setInt(3, model.getCapacity());
        stmt.setInt(4, model.getBed_number());
        stmt.setString(5, model.getRoom_type());
        stmt.setDouble(6, model.getPrice());
        stmt.executeUpdate();

        ResultSet res = conn.prepareStatement("SELECT room_number FROM rooms ORDER BY room_number DESC LIMIT 1").executeQuery();
        res.next();
        int id = res.getInt("room_number");

        return find(id);
    }

    public static Rooms update(Rooms model) throws Exception {
        Connection conn = dbConnection.getConnection();

        PreparedStatement stmt = conn
                .prepareStatement("UPDATE rooms SET room_number = ?, floor_number = ?, bed_number = ?, room_type = ?, price = ? WHERE room_number = ?");

        stmt.setInt(1, model.getRoom_number());
        stmt.setInt(2, model.getFloor_number());
        stmt.setInt(3, model.getBed_number());
        stmt.setString(4, model.getRoom_type());
        stmt.setDouble(5, model.getPrice());
        stmt.setInt(6, model.getRoom_number());
        stmt.executeUpdate();

        return find(model.getRoom_number());
    }

    public static boolean remove(int id) throws Exception {
        Connection conn = dbConnection.getConnection();

        PreparedStatement stmt = conn.prepareStatement("DELETE FROM rooms WHERE room_number = ?");
        stmt.setInt(1, id);
        return stmt.executeUpdate() >= 1;
    }

    private static Rooms parseFromRes(ResultSet res) throws Exception {
        int room_number = res.getInt("room_number");
        int floor_number = res.getInt("floor_number");
        int capacity = res.getInt("capacity");
        int bed_number = res.getInt("bed_number");
        String room_type = res.getString("room_type");
        double price = res.getDouble("price");

        return new Rooms(room_number, floor_number, capacity, bed_number, room_type, price);
    }
    
    public ResultSet getAvailableRooms(String checkin,String checkout,String type) throws Exception{
        Connection connection=dbConnection.getConnection();
        String query;
        
        if(type=="All"){
            query="select * from rooms r where r.room_number not in(\n" +
                    "select r.room_number \n" +
                    "from reservations res inner join rooms r on res.room_id=r.room_number\n" +
                    "where (checkin_date between '"+checkin+"' and '"+checkout+"') and (checkout_date between '"+checkin+"' and '"+checkout+"'))";
        }
    }
}
