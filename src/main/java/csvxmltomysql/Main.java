package csvxmltomysql;

import java.sql.SQLException;

public class Main {

    public static void main(String... args) throws SQLException {

        String fileName = "dane-osoby.csv";

        CsvToSql.readCsvFile(fileName);
        CsvToSql.saveDataToSql();

    }
}

