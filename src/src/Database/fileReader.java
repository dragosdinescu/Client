package src.Database;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class fileReader {

    private String location;

    /**
     *
     * @param location @String
     * gets the location of the txt file
     */
    public fileReader(String location) {
        this.location=location;
    }

    /**
     * Reads the lines from the txt file
     * @return a list with all the lines that the file contains
     */
    public List<String> readLines() {
        Path path = Paths.get(location);
        try {
            List<String> allLines = Files.readAllLines(path, StandardCharsets.UTF_8);
            return allLines;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }
}
