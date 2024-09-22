package org.example.forkjoin;

import org.example.LoadFiles;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;

import static org.example.Configs.MULTITASK;


public class ForkJoin {

    public static void executar() {
        System.out.println("Executando ForkJoin");
        long initialTimeTotalExecution = System.currentTimeMillis();

        LoadFiles loadFiles = new LoadFiles();
        HashMap<String, Long> resultadoTempo = new HashMap<>();
        List<String> palavras = MULTITASK ? loadFiles.getPalavras() : Collections.singletonList("Tom Byrd");
        for (String searchTerm : palavras) {
            ForkJoinPool pool = new ForkJoinPool(Math.min(16, Runtime.getRuntime().availableProcessors() * 2));

            long startTime = System.currentTimeMillis();

            FileSearchTask task = new FileSearchTask(loadFiles.getFiles(), searchTerm);
            String result = pool.invoke(task);

            long endTime = System.currentTimeMillis();

            if (result != null) {
                resultadoTempo.put("nome: " + searchTerm + " " + result, endTime - startTime);
            } else {
                System.out.println("Nome não encontrado.");
            }
        }
        long finalTimeTotalExecution = System.currentTimeMillis();
        System.out.printf("\nExecutado em %s ms: ", (finalTimeTotalExecution - initialTimeTotalExecution));
//        System.out.println("\nResultado ordenado pelo tempo de execução:");
//        resultadoTempo.entrySet().stream()
//                .sorted(Map.Entry.comparingByValue())
//                .forEach(entry -> System.out.printf("%s - Tempo: %d ms%n \n", entry.getKey(), entry.getValue()));
    }
}
