package csvxmltomysql.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private final static String url = "jdbc:mysql://localhost:3306/sqldb?useSSL=false";
    private final static String username = "root";
    private final static String password = "";
    private static Connection conn = null;

    public static Connection getConnection() {

        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
