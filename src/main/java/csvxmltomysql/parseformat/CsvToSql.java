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

import static csvxmltomysql.parseformat.CheckContactType.isJabber;
import static csvxmltomysql.parseformat.CheckContactType.isTelNumber;
import static csvxmltomysql.parseformat.CheckContactType.isEmail;

public class CsvToSql {

    public void readAndSaveCsvFile(String fileName) {
        SqlService sqlService = new SqlService();
        Charset charset = Charset.forName("utf-8");
        Path path = Paths.get(fileName);
        String line;
        int customerId = sqlService.findMaxCustomersId() + 1;
        int contactId = sqlService.findMaxContactsId() + 1;

        try (BufferedReader reader = Files.newBufferedReader(path, charset)) {

            while ((line = reader.readLine()) != null) {
                String[] splitLine = line.split(",");
                String name, surname, age, city, contact;
                int type;

                name = splitLine[0];
                surname = splitLine[1];
                age = splitLine[2];
                city = splitLine[3];

                for (int i = 4; i < splitLine.length; i++) {
                    contact = splitLine[i];
                    if (isEmail(contact)) {
                        type = 1;
                    } else if (isTelNumber(contact)) {
                        type = 2;
                    } else if (isJabber(contact)) {
                        type = 3;
                    } else {
                        type = 0;
                    }
                    sqlService.saveContactToSql(new Contact(contactId, customerId, type, contact));
                    contactId++;
                }

                Customer customer = new Customer(customerId, name, surname, age, city);
                sqlService.saveCustomerToSql(customer);
                customerId++;
            }
        } catch (IOException ex) {
            System.out.println("The file could not be loaded: " + fileName);
        }
        System.out.println("Successfully uploaded sql data!");
    }
}