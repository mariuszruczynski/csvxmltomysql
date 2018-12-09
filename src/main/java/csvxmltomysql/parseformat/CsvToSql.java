package csvxmltomysql.parseformat;

import csvxmltomysql.model.CheckContactType;
import csvxmltomysql.model.Contact;
import csvxmltomysql.model.Customer;
import csvxmltomysql.service.sqlService;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static csvxmltomysql.model.CheckContactType.isAlphabetic;
import static csvxmltomysql.model.CheckContactType.isNumeric;
import static csvxmltomysql.service.sqlService.saveCustomerToSql;

public class CsvToSql {

    public static void readAndSaveCsvFile(String fileName) {

        Charset charset = Charset.forName("utf-8");
        Path path = Paths.get(fileName);
        String line;
        int customerId = 1;
        int contactId = 1;

        try (BufferedReader reader = Files.newBufferedReader(path, charset)) {

            while ((line = reader.readLine()) != null) {
                String[] splitLine = line.split(",");
                String name, surname, age, city, contact;
                int type = 0;

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
                for (int i = 4; i < splitLine.length; i++) {
                    contact = splitLine[i];
                    if (contact.contains("@")) {
                        type = 1;
                    } else if (isNumeric(contact)) {
                        type = 2;
                    } else if (isAlphabetic(contact)) {
                        type = 3;
                    } else {
                        type = 0;
                    }
                    sqlService.saveContactToSql(new Contact(contactId, customerId, type, contact));
                    contactId++;
                }

                Customer customer = new Customer(customerId, name, surname, age, city);
                saveCustomerToSql(customer);
                customerId++;
            }
        } catch (IOException ex) {
            System.out.println("The file could not be loaded: " + fileName);
        }
        System.out.println("Successfully uploaded sql data!");
    }
}