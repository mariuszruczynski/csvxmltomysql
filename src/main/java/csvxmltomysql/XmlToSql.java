package csvxmltomysql;

import org.xml.sax.InputSource;
import org.xml.sax.helpers.DefaultHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class XmlToSql extends DefaultHandler {

    private Customer customer;
    private String temp;
    private int i =1;
    public static ArrayList<Customer> customers = new ArrayList<Customer>();
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
                             String qName, Attributes attributes) throws SAXException {
        temp = "";
        if (qName.equalsIgnoreCase("person")) {
            customer = new Customer();
            customer.setId(i);
            i++;
        }
    }

    public void endElement(String uri, String localName, String qName)
            throws SAXException {

        if (qName.equalsIgnoreCase("person")) {
            customers.add(customer);
        } else if (qName.equalsIgnoreCase("name")) {
            customer.setName(temp);
        } else if (qName.equalsIgnoreCase("surname")) {
            customer.setSurname(temp);
        } else if (qName.equalsIgnoreCase("age")) {
            customer.setAge(temp);
        } else if (qName.equalsIgnoreCase("city")) {
            customer.setCity(temp);
        }
    }
}
