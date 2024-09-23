package org.example.unithread;

import org.example.Configs;
import org.example.FileUtils;
import org.example.LoadFiles;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

import static org.example.Configs.MULTITASK;

public class UniThread {

    public static void executar() {
        System.out.println("Sem Paralelismo");
        long initialTimeTotalExecution = System.currentTimeMillis();

        LoadFiles loadFiles = new LoadFiles();
        HashMap<String, Long> resultadoTempo = new HashMap<>();

        List<String> palavras = MULTITASK ? loadFiles.getPalavras() : Collections.singletonList(Configs.SEARCH_TERM);
        for (String searchTerm : palavras) {
            long startTime = System.currentTimeMillis();

            for (File file : loadFiles.getFiles()) {
                String result = FileUtils.searchInFile(file, searchTerm);
                if (result != null) {
                    long endTime = System.currentTimeMillis();
                    resultadoTempo.put(searchTerm + " " + result, endTime - startTime);
                    break;
                }
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
