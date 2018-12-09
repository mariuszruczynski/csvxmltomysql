package csvxmltomysql.service;

import csvxmltomysql.model.Customer;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SaveCustomerToSQLTest {

    private Customer customer = null;
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

    @Test
    void addCustomerFindByIdAndCheckIsExistInDb() {

        int id = 1055;
        String name = "Maria";
        String surname = "Kowalska";
        String age = "25";
        String city = "Warszawa";
        Customer testCustomer = new Customer(id, name, surname, age, city);

        SaveCustomerToSQL.saveCustomerToSql(testCustomer);

        findCustomerById(1055);

        assertEquals(customer.getName(), name);
        assertEquals(customer.getSurname(), surname);
        assertEquals(customer.getAge(), age);
        assertEquals(customer.getCity(), city);

        delCustomerById(id);
    }
}
