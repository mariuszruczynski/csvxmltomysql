package csvxmltomysql;

import csvxmltomysql.parseformat.CsvToSql;
import csvxmltomysql.parseformat.XmlToSql;

import static csvxmltomysql.parseformat.CsvToSql.readAndSaveCsvFile;
import static csvxmltomysql.parseformat.XmlToSql.readAndSaveXML;

public class Main {

    public static void main(String... args) {

        String csvFileName = "dane-osoby.csv";
        String xmlFileName = "dane-osoby.xml";


        //readAndSaveXML(xmlFileName);
        readAndSaveCsvFile(csvFileName);

    }
}

