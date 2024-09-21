package org.example;

import lombok.Getter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@Getter
public class LoadFiles {

    private List<String> palavras = new ArrayList<>();
    private List<File> files = new ArrayList<>();

    public LoadFiles() {
        String directoryPath = "src/main/resources/arquivos/";

        File folder = new File(directoryPath);
        File[] filesArray = folder.listFiles((dir, name) -> name.endsWith(".txt"));

        if (filesArray == null || filesArray.length == 0) {
            System.out.println("Nenhum arquivo .txt encontrado.");
            return;
        }

        files = List.of(filesArray);
        palavras = files.stream()
                .flatMap(file -> {
                    try {
                        return Files.lines(file.toPath());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }).toList();
    }
}
