package csvxmltomysql;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String... args) {

        String filename = "dane-osoby.csv";
        Charset charset = Charset.forName("utf-8");
        Path path = Paths.get(filename);
        String line;

        try (BufferedReader reader = Files.newBufferedReader(path, charset)){

            do {
                line = reader.readLine();
                if (line != null) {
                    System.out.println(line);
                }
            } while (line != null);
        } catch (IOException ex) {
            System.out.println("The file could not be loaded: " + filename);
        }

    }
}

