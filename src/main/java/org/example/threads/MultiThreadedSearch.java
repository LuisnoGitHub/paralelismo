package org.example.threads;

import org.example.Configs;
import org.example.LoadFiles;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import static org.example.Configs.MULTITASK;

public class MultiThreadedSearch {


    public static void executar() throws InterruptedException {
        System.out.println("Multithread");
        long initialTimeTotalExecution = System.currentTimeMillis();
        LoadFiles loadFiles = new LoadFiles();

        HashMap<String, Long> resultadoTempo = new HashMap<>();
        List<String> palavras = MULTITASK ? loadFiles.getPalavras() : Collections.singletonList(Configs.SEARCH_TERM);

        CountDownLatch latch = new CountDownLatch(palavras.size());

        for (String searchTerm : palavras) {
            Thread thread = new Thread(new FileSearchTask(loadFiles.getFiles(), searchTerm, resultadoTempo, latch));
            thread.start();
        }
        latch.await();

        long finalTimeTotalExecution = System.currentTimeMillis();
        if(MULTITASK){
            System.out.println("\nResultado ordenado pelo tempo de execução:");

        }
        resultadoTempo.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(entry -> System.out.printf("%s\n%d ms%n", entry.getKey(), entry.getValue()));
        System.out.printf("%s ms", (finalTimeTotalExecution - initialTimeTotalExecution));
    }
}
