package org.example.forkjoin;

import org.example.Configs;
import org.example.LoadFiles;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;

import static org.example.Configs.MULTITASK;


public class ForkJoin {

    public static void executar() {
        System.out.println("ForkJoin");
        long initialTimeTotalExecution = System.currentTimeMillis();

        LoadFiles loadFiles = new LoadFiles();
        HashMap<String, Long> resultadoTempo = new HashMap<>();
        List<String> palavras = MULTITASK ? loadFiles.getPalavras() : Collections.singletonList(Configs.SEARCH_TERM);
        for (String searchTerm : palavras) {
            ForkJoinPool pool = new ForkJoinPool(Math.min(16, Runtime.getRuntime().availableProcessors() * 2));

            long startTime = System.currentTimeMillis();

            FileSearchTask task = new FileSearchTask(loadFiles.getFiles(), searchTerm);
            String result = pool.invoke(task);

            long endTime = System.currentTimeMillis();

            if (result != null) {
                resultadoTempo.put(searchTerm + result, endTime - startTime);
            } else {
                System.out.println("Nome não encontrado.");
            }
        }
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
