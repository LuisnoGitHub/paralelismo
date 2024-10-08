package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.CountDownLatch;

public class FileUtils {
    public static String searchInFile(File file, String searchTerm) {
        try (BufferedReader reader = Files.newBufferedReader(file.toPath())) {
            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (line.contains(searchTerm)) {
                    return "\n" + file.getName() + "\n" + lineNumber;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
