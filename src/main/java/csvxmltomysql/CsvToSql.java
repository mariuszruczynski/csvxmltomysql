package csvxmltomysql;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CsvToSql {

    public static void readAndSaveCsvFile(String fileName) {

        Charset charset = Charset.forName("utf-8");
        Path path = Paths.get(fileName);
        String line;
        int i = 0;

        try (BufferedReader reader = Files.newBufferedReader(path, charset)) {

            while ((line = reader.readLine()) != null) {
                String[] splitLine = line.split(",");
                String name, surname, age, city;
                Integer id;

                id = (i + 1);
                if (splitLine[0] != null) {
                    name = splitLine[0];
                } else {
                    name = "";
                }
                if (splitLine[1] != null) {
                    surname = splitLine[1];
                } else
                    surname = "";
                if (splitLine[2] != null) {
                    age = splitLine[2];
                } else {
                    age = "";
                }
                if (splitLine[3] != null) {
                    city = splitLine[3];
                } else {
                    city = "";
                }
                Customer customer = new Customer(id, name, surname, age, city);
                saveDataToSql(customer);
                i++;
            }
        } catch (IOException ex) {
            System.out.println("The file could not be loaded: " + fileName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Successfully uploaded sql data!");

    }

    private static void saveDataToSql(Customer customer) throws SQLException {

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
