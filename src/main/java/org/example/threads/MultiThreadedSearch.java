package org.example.threads;

import org.example.LoadFiles;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import static org.example.Configs.MULTITASK;

public class MultiThreadedSearch {


    public static void executar() throws InterruptedException {
        System.out.println("Executando Multithread");

        LoadFiles loadFiles = new LoadFiles();

        HashMap<String, Long> resultadoTempo = new HashMap<>();
        List<String> palavras = MULTITASK ? loadFiles.getPalavras() : Collections.singletonList("Heather Edwards");

        CountDownLatch latch = new CountDownLatch(palavras.size());

         for (String searchTerm : palavras) {
            Thread thread = new Thread(new FileSearchTask(loadFiles.getFiles(), searchTerm, resultadoTempo, latch));
            thread.start();
        }
        latch.await();

        System.out.println("\nResultado ordenado pelo tempo de execução:");
        resultadoTempo.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(entry -> System.out.printf("%s - Tempo: %d ms%n \n", entry.getKey(), entry.getValue()));
    }
}
