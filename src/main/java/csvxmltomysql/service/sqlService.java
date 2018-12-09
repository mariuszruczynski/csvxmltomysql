package csvxmltomysql.service;

import csvxmltomysql.model.Contact;
import csvxmltomysql.model.Customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class sqlService {

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
            String insertSQL = "INSERT INTO customers VALUES (?,?,?,?)";
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
}
