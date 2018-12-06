package csvxmltomysql;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CsvToSql {

    public static List<Person> personList = new ArrayList<>();

    public static void readCsvFile(String fileName) {

        Charset charset = Charset.forName("utf-8");
        Path path = Paths.get(fileName);
        String line = "";
        String splitBy = ",";
        int i = 0;

        try (BufferedReader reader = Files.newBufferedReader(path, charset)) {

            while ((line = reader.readLine()) != null) {
                String[] splitLine = line.split(splitBy);
                String name, surname, age, city;
                Integer id;

                id = (i + 1);
                if (splitLine[0] != null) {
                    name = splitLine[0];
                } else {
                    name = "";
                }
                if (splitLine[1] != null) {
                    surname = splitLine[1];
                } else
                    surname = "";
                if (splitLine[2] != null) {
                    age = splitLine[2];
                } else {
                    age = "";
                }
                if (splitLine[3] != null) {
                    city = splitLine[3];
                } else {
                    city = "";
                }
                personList.add(new Person(id, name, surname, age, city));
                i++;
            }
        } catch (IOException ex) {
            System.out.println("The file could not be loaded: " + fileName);
        }


    }

    public static void showList() {
        for (Person e : personList) {
            System.out.println(e.getId() + " " + e.getName() + " " + e.getSurname() + " " + " " + e.getAge() + " " + e.getCity());
        }
    }
}
