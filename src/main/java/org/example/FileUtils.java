package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileUtils {
    public static String searchInFile(File file, String searchTerm) {
        try (BufferedReader reader = Files.newBufferedReader(file.toPath())) {
            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (line.contains(searchTerm)) {
                    return "Arquivo: " + file.getName() + " Linha: " + lineNumber;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
