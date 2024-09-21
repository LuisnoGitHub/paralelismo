package org.example.threads;

import lombok.AllArgsConstructor;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

@AllArgsConstructor
public class FileSearchTask implements Runnable {
    private final List<File> files;
    private final String searchTerm;
    private final Map<String, Long> resultadoTempo;
    private final CountDownLatch latch;

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();

        for (File file : files) {
            String result = searchInFile(file, searchTerm);
            if (result != null) {
                long endTime = System.currentTimeMillis();
                synchronized (resultadoTempo) {
                    resultadoTempo.put("nome: " + searchTerm + " " + result, endTime - startTime);
                }
                break;
            }
        }

        latch.countDown(); // Indica que esta thread terminou
    }

    private String searchInFile(File file, String searchTerm) {
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
