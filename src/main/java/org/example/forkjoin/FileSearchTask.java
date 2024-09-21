package org.example.forkjoin;

import lombok.AllArgsConstructor;
import org.example.FileUtils;

import java.io.File;
import java.util.List;
import java.util.concurrent.RecursiveTask;

@AllArgsConstructor
public class FileSearchTask extends RecursiveTask<String> {
    private final List<File> files;
    private final String searchTerm;
    private static final int THRESHOLD = 5;

    @Override
    protected String compute() {
        if (files.size() <= THRESHOLD) {
            for (File file : files) {
                if (isCancelled()) {
                    return null;
                }
                String result = FileUtils.searchInFile(file, searchTerm);
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
}
