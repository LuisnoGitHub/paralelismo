package org.example.threads;

import lombok.AllArgsConstructor;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import static org.example.FileUtils.searchInFile;

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
                    resultadoTempo.put(searchTerm + " " + result, endTime - startTime);
                }
                break;
            }
        }

        latch.countDown(); // Indica que esta thread terminou
    }
}
