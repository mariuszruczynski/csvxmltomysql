package csvxmltomysql.service;

import csvxmltomysql.model.Contact;
import csvxmltomysql.model.Customer;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class sqlServiceTest {

    private Customer customer = null;
    private Contact contact = null;
    private String url = "jdbc:mysql://localhost:3306/sqldb?useSSL=false";
    private String username = "root";
    private String password = "";
    private Connection conn;

    private Customer findCustomerById(Integer id) {

        try {
            conn = DriverManager.getConnection(url, username, password);
            String query = "SELECT * FROM customers WHERE id = ? ";
            PreparedStatement ps;
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs;
            rs = ps.executeQuery();
            if (rs.next()) {
                customer = new Customer(rs.getInt(1),
                        rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    private void delCustomerById(Integer id) {

        try {
            conn = DriverManager.getConnection(url, username, password);
            String query = "DELETE  FROM customers WHERE id = ? ";
            PreparedStatement ps;
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Contact findContactById(Integer id) {

        try {
            conn = DriverManager.getConnection(url, username, password);
            String query = "SELECT * FROM contacts WHERE id = ? ";
            PreparedStatement ps;
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs;
            rs = ps.executeQuery();
            if (rs.next()) {
                contact = new Contact(rs.getInt(1),
                        rs.getInt(2), rs.getInt(3),
                        rs.getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contact;
    }

    private void delContactById(Integer id) {
        try {
            conn = DriverManager.getConnection(url, username, password);
            String query = "DELETE  FROM contacts WHERE id = ? ";
            PreparedStatement ps;
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void addCustomerFindByIdAndCheckIsExistInDb() {

        int id = 1055;
        String name = "Maria";
        String surname = "Kowalska";
        String age = "25";
        String city = "Warszawa";
        Customer testCustomer = new Customer(id, name, surname, age, city);

        sqlService.saveCustomerToSql(testCustomer);

        findCustomerById(1055);

        assertEquals(customer.getName(), name);
        assertEquals(customer.getSurname(), surname);
        assertEquals(customer.getAge(), age);
        assertEquals(customer.getCity(), city);

        delCustomerById(id);
    }

    @Test
    void addContactFindByIdAndCheckIsExistInDb() {

        Integer id = 1055;
        Integer customerId = 1000;
        Integer contactType = 2;
        String con = "25252525";
        Contact testContact = new Contact(id, customerId, contactType, con);

        sqlService.saveContactToSql(testContact);

        findContactById(1055);

        assertEquals(contact.getCustomerId(), customerId);
        assertEquals(contact.getType(),contactType);
        assertEquals(contact.getContact(), con);

        delContactById(id);
    }
}
