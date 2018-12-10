package csvxmltomysql;

import csvxmltomysql.service.SqlService;

import java.util.Scanner;

import static csvxmltomysql.parseformat.CsvToSql.readAndSaveCsvFile;
import static csvxmltomysql.parseformat.XmlToSql.readAndSaveXML;

public class Main {

    public static void main(String... args) {

        Scanner sc = new Scanner(System.in);
        String csvFileName = "dane-osoby.csv";
        String xmlFileName = "dane-osoby.xml";

        System.out.println();
        System.out.println("Which type we read:");
        System.out.println("1. CSV");
        System.out.println("2. XML");
        int option = Integer.parseInt(sc.nextLine());
        if (option == 1) {
            readAndSaveCsvFile(csvFileName);
        } else if (option == 2) {
            readAndSaveXML(xmlFileName);
        } else {
            System.out.println("wrong choice");
        }
    }
}
