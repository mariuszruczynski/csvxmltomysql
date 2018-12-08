package csvxmltomysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SaveCustomerToSQL {

    public static void saveCustomerToSql(Customer customer) throws SQLException {

        String url = "jdbc:mysql://localhost:3306/sqldb?useSSL=false";
        String username = "root";
        String password = "";
        Connection conn = DriverManager.getConnection(url, username, password);

        String insertSQL = "INSERT INTO customers VALUES (?,?,?,?,?)";

        PreparedStatement pstmt = conn.prepareStatement(insertSQL);

        pstmt.setInt(1, customer.getId());
        pstmt.setString(2, customer.getName());
        pstmt.setString(3, customer.getSurname());
        pstmt.setString(4, customer.getAge());
        pstmt.setString(5, customer.getCity());

        pstmt.executeUpdate();
    }
}
