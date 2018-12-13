package csvxmltomysql.service;

import csvxmltomysql.model.Contact;
import csvxmltomysql.model.Customer;

import java.sql.*;

public class SqlService {

    private int maxCustomersId;
    private int maxContactId;

    private Connection conn = null;


    public void saveCustomerToSql(Customer customer) {

        conn = ConnectionManager.getConnection();

        String insertSQL = "INSERT INTO customers VALUES (?,?,?,?,?)";
        PreparedStatement pstmt;
        try {
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

    public void saveContactToSql(Contact contact) {

        conn = ConnectionManager.getConnection();
        String insertSQL = "INSERT INTO contacts VALUES (?,?,?,?)";
        PreparedStatement pstmt;
        try {
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

    public int findMaxCustomersId() {

        conn = ConnectionManager.getConnection();

        try {
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

    public int findMaxContactsId() {

        conn = ConnectionManager.getConnection();
        try {
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
