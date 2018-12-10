package csvxmltomysql.parseformat;

import csvxmltomysql.model.Contact;
import csvxmltomysql.model.Customer;
import csvxmltomysql.service.SqlService;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static csvxmltomysql.model.CheckContactType.isJabber;
import static csvxmltomysql.model.CheckContactType.insTelNumber;
import static csvxmltomysql.model.CheckContactType.isMail;
import static csvxmltomysql.service.SqlService.findMaxContactsId;
import static csvxmltomysql.service.SqlService.findMaxCustomersId;
import static csvxmltomysql.service.SqlService.saveCustomerToSql;

public class CsvToSql {

    public static void readAndSaveCsvFile(String fileName) {

        Charset charset = Charset.forName("utf-8");
        Path path = Paths.get(fileName);
        String line;
        int customerId = findMaxCustomersId() + 1;
        int contactId = findMaxContactsId() + 1;

        try (BufferedReader reader = Files.newBufferedReader(path, charset)) {

            while ((line = reader.readLine()) != null) {
                String[] splitLine = line.split(",");
                String name, surname, age, city, contact;
                int type;

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
                    if (isMail(contact)) {
                        type = 1;
                    } else if (insTelNumber(contact)) {
                        type = 2;
                    } else if (isJabber(contact)) {
                        type = 3;
                    } else {
                        type = 0;
                    }
                    SqlService.saveContactToSql(new Contact(contactId, customerId, type, contact));
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