package DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class  dbConnection {
    public static Connection connection;
    public static Connection getConnection() throws Exception {
        String userName = "root";
        String password = "riseandshine";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/dbhotel?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UCT";
            connection = DriverManager.getConnection(url,userName,password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
