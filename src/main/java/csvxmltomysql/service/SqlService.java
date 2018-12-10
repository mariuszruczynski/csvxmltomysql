package csvxmltomysql.service;

import csvxmltomysql.model.Contact;
import csvxmltomysql.model.Customer;

import java.sql.*;

public class SqlService {

    private static int maxCustomersId;
    private static int maxContactId;

    private static String url = "jdbc:mysql://localhost:3306/sqldb?useSSL=false";
    private static String username = "root";
    private static String password = "";
    private static Connection conn = null;

    public static void saveCustomerToSql(Customer customer) {

        try {
            conn = DriverManager.getConnection(url, username, password);
            String insertSQL = "INSERT INTO customers VALUES (?,?,?,?,?)";
            PreparedStatement pstmt;
            pstmt = conn.prepareStatement(insertSQL);
            pstmt.setInt(1, customer.getId());
            pstmt.setString(2, customer.getName());
            pstmt.setString(3, customer.getSurname());
            pstmt.setString(4, customer.getAge());
            pstmt.setString(5, customer.getCity());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void saveContactToSql(Contact contact) {

        try {
            conn = DriverManager.getConnection(url, username, password);
            String insertSQL = "INSERT INTO contacts VALUES (?,?,?,?)";
            PreparedStatement pstmt;
            pstmt = conn.prepareStatement(insertSQL);
            pstmt.setInt(1, contact.getId());
            pstmt.setInt(2, contact.getCustomerId());
            pstmt.setInt(3, contact.getType());
            pstmt.setString(4, contact.getContact());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int findMaxCustomersId() {
        try {
            conn = DriverManager.getConnection(url, username, password);
            String query = "SELECT MAX(id) FROM customers";
            ResultSet rs;
            rs = conn.createStatement()
                    .executeQuery(query);
            rs.next();
            maxCustomersId = rs.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maxCustomersId;
    }

    public static int findMaxContactsId() {
        try {
            conn = DriverManager.getConnection(url, username, password);
            String query = "SELECT MAX(id) FROM contacts";
            ResultSet rs;
            rs = conn.createStatement()
                    .executeQuery(query);
            rs.next();
            maxContactId = rs.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maxContactId;
    }


}
