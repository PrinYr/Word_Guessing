package server.Reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class fileReader {

    private static final String FILEPATH = Paths.get(System.getProperty("user.dir"), "src", "server", "Reader", "oxford_words.txt").toString();

    public String readFile() throws IOException {
        List<String> oxfordWords = new ArrayList<>();

        try (BufferedReader buffer = new BufferedReader(new FileReader(FILEPATH))) {
            String word;
            while ((word = buffer.readLine()) != null) {
                oxfordWords.add(word);
            }
        }

        if (oxfordWords.isEmpty()) {
            throw new IOException("The file is empty");
        }

        return oxfordWords.get(ThreadLocalRandom.current().nextInt(oxfordWords.size()));
    }
}
