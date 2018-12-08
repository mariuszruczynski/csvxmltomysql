package csvxmltomysql;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;

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
                SaveCustomerToSQL.saveCustomerToSql(customer);
                i++;
            }
        } catch (IOException ex) {
            System.out.println("The file could not be loaded: " + fileName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Successfully uploaded sql data!");
    }
}
