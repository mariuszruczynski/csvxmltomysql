package csvxmltomysql;

public class Main {

    public static void main(String... args) {

        String fileName = "dane-osoby.csv";


        CsvToSql.readCsvFile(fileName);

        CsvToSql.showList();
    }
}

