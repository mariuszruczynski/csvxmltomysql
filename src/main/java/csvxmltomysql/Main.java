package csvxmltomysql;

import java.sql.SQLException;

public class Main {

    public static void main(String... args) {

        String fileName = "dane-osoby.csv";

        CsvToSql.readAndSaveCsvFile(fileName);

    }
}

