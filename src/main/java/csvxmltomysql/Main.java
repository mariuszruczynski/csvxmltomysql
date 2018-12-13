package csvxmltomysql;

import csvxmltomysql.parseformat.CsvToSql;
import csvxmltomysql.parseformat.XmlToSql;

import java.util.Scanner;


public class Main {

    public static void main(String... args) {
        XmlToSql xmlToSql = new XmlToSql();
        CsvToSql csvToSql = new CsvToSql();

        Scanner sc = new Scanner(System.in);
        String csvFileName = "dane-osoby.csv";
        String xmlFileName = "dane-osoby.xml";
        boolean exit = false;

        while (!exit) {
            System.out.println("\nWhich type file read (select: 1 or 2):");
            System.out.println("1. CSV");
            System.out.println("2. XML");
            System.out.println("3. Exit");
            int option = Integer.parseInt(sc.nextLine());
            switch (option) {
                case 1:
                    csvToSql.readAndSaveCsvFile(csvFileName);
                    break;
                case 2:
                    xmlToSql.readAndSaveXML(xmlFileName);
                    break;
                default:
                    exit = true;
            }
        }
    }
}

