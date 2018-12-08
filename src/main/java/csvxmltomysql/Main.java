package csvxmltomysql;

import java.sql.SQLException;
import java.util.List;

public class Main {

    public static boolean isNumeric(String strNum) {
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }

    public static void main(String... args) {

        String csvFileName = "dane-osoby.csv";
        String xmlFileName = "dane-osoby.xml";

        XmlToSql.readAndSaveXML(xmlFileName);
        //CsvToSql.readAndSaveCsvFile(fileName);

    }
}

