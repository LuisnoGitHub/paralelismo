package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoin {

    static class FileSearchTask extends RecursiveTask<String> {
        private final List<File> files;
        private final String searchTerm;
        private static final int THRESHOLD = 5;

        public FileSearchTask(List<File> files, String searchTerm) {
            this.files = files;
            this.searchTerm = searchTerm;
        }

        @Override
        protected String compute() {
            if (files.size() <= THRESHOLD) {
                for (File file : files) {
                    if (isCancelled()) {
                        return null;
                    }
                    String result = searchInFile(file, searchTerm);
                    if (result != null) {
                        complete(result);
                        return result;
                    }
                }
                return null;
            } else {
                int mid = files.size() / 3;
                FileSearchTask leftTask = new FileSearchTask(files.subList(0, mid), searchTerm);
                FileSearchTask rightTask = new FileSearchTask(files.subList(mid, files.size()), searchTerm);

                leftTask.fork();

                String rightResult = rightTask.compute();
                String leftResult = leftTask.join();

                return rightResult != null ? rightResult : leftResult;
            }
        }

        private String searchInFile(File file, String searchTerm) {
            try (BufferedReader reader = Files.newBufferedReader(file.toPath())) {
                String line;
                int lineNumber = 0;

                while ((line = reader.readLine()) != null) {
                    lineNumber++;
                    if (isCancelled()) {
                        return null;
                    }
                    if (line.contains(searchTerm)) {
                        return "Arquivo: " + file.getName() + "\nLinha: " + lineNumber;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public static void main(String[] args) {
        String directoryPath = "C:/Users/IMPACTO/Documents/dev/paralelismo/dataset_g";
        String searchTerm = "Alicia Orr";

        File folder = new File(directoryPath);
        File[] filesArray = folder.listFiles((dir, name) -> name.endsWith(".txt"));

        if (filesArray == null || filesArray.length == 0) {
            System.out.println("Nenhum arquivo .txt encontrado.");
            return;
        }

        List<File> files = List.of(filesArray);

        ForkJoinPool pool = new ForkJoinPool(Math.min(16, Runtime.getRuntime().availableProcessors() * 2));

        long startTime = System.currentTimeMillis();

        FileSearchTask task = new FileSearchTask(files, searchTerm);
        String result = pool.invoke(task);

        long endTime = System.currentTimeMillis();

        if (result != null) {
            System.out.println(result);
        } else {
            System.out.println("Nome não encontrado.");
        }

        System.out.println("Tempo: " + (endTime - startTime) + " ms");
    }
}
