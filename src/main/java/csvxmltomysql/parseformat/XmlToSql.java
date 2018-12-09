package csvxmltomysql.parseformat;

import csvxmltomysql.model.Contact;
import csvxmltomysql.model.Customer;
import csvxmltomysql.service.sqlService;
import org.xml.sax.InputSource;
import org.xml.sax.helpers.DefaultHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;

import static csvxmltomysql.service.sqlService.saveContactToSql;

public class XmlToSql extends DefaultHandler {

    private Customer customer;
    private String temp;
    private int customerId = 1;
    private int contactId = 1;
    private int contactType;
    private List<String> phones = new ArrayList<>();
    private List<String> emails = new ArrayList<>();
    private List<String> jabbers = new ArrayList<>();
    private List<String> icq = new ArrayList<>();

    public static void readAndSaveXML(String fileName) {

        try {

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            File xmlFile = new File(fileName);
            InputStream inputStream = new FileInputStream(xmlFile);
            InputStreamReader inputReader = new InputStreamReader(inputStream, "UTF-8");
            InputSource inputSource = new InputSource(inputReader);
            inputSource.setEncoding("UTF-8");
            saxParser.parse(inputSource, new XmlToSql());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void characters(char[] buffer, int start, int length) {
        temp = new String(buffer, start, length);
    }

    public void startElement(String uri, String localName,
                             String qName, Attributes attributes) {
        temp = "";
        if (qName.equalsIgnoreCase("person")) {
            customer = new Customer();
            customer.setId(customerId);
            customerId++;
            phones.clear();
            jabbers.clear();
            emails.clear();
            icq.clear();
        }
    }

    public void endElement(String uri, String localName, String qName) {

        if (qName.equalsIgnoreCase("Person")) {
            for (String p : phones) {
                contactType = 2;
                saveContactToSql(new Contact(contactId, customerId-1, contactType, p));
                contactId++;
            }
            for (String e : emails) {
                contactType = 1;
                saveContactToSql(new Contact(contactId, customerId-1, contactType, e));
                contactId++;
            }
            for (String j : jabbers) {
                contactType = 3;
                saveContactToSql(new Contact(contactId, customerId-1, contactType, j));
                contactId++;
            }
            for (String ic : icq) {
                contactType = 0;
                saveContactToSql(new Contact(contactId, customerId-1, contactType, ic));
                contactId++;
            }
            sqlService.saveCustomerToSql(customer);
        } else if (qName.equalsIgnoreCase("Name")) {
            customer.setName(temp);
        } else if (qName.equalsIgnoreCase("Surname")) {
            customer.setSurname(temp);
        } else if (qName.equalsIgnoreCase("Age")) {
            customer.setAge(temp);
        } else if (qName.equalsIgnoreCase("City")) {
            customer.setCity(temp);
        } else if (qName.equalsIgnoreCase("Phone")) {
            phones.add(temp);
        } else if (qName.equalsIgnoreCase("Email")) {
            emails.add(temp);
        } else if (qName.equalsIgnoreCase("Jabber")) {
            jabbers.add(temp);
        } else if (qName.equalsIgnoreCase("Icq")) {
            icq.add(temp);
        }
    }
}