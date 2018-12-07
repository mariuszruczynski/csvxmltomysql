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

    public static List<Customer> customerList = new ArrayList<>();

    public static void readCsvFile(String fileName) {

        Charset charset = Charset.forName("utf-8");
        Path path = Paths.get(fileName);
        String line = "";
        String splitBy = ",";
        int i = 0;

        try (BufferedReader reader = Files.newBufferedReader(path, charset)) {

            while ((line = reader.readLine()) != null) {
                String[] splitLine = line.split(splitBy);
                String name, surname, age, city;
                Integer id;

                id = (i);
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
                customerList.add(new Customer(id, name, surname, age, city));
                i++;
            }
        } catch (IOException ex) {
            System.out.println("The file could not be loaded: " + fileName);
        }
    }

    public static void saveDataToSql() throws SQLException {

        String url = "jdbc:mysql://localhost:3306/sqldb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String username = "root";
        String password = "hipcia";
        Connection conn = DriverManager.getConnection(url, username, password);

        String insertSQL = "INSERT INTO customers VALUES (?,?,?,?,?)";

        for (int i = 0; i < (customerList.size()); i++) {
            PreparedStatement pstmt = conn.prepareStatement(insertSQL);

            pstmt.setInt(1, customerList.get(i).getId());
            pstmt.setString(2, customerList.get(i).getName());
            pstmt.setString(3, customerList.get(i).getSurname());
            pstmt.setString(4, customerList.get(i).getAge());
            pstmt.setString(5, customerList.get(i).getCity());

            pstmt.executeUpdate();
        }

        System.out.println("Successfully uploaded sql data!");
    }
}
