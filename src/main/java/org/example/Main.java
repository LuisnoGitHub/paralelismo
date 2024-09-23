package org.example;

import org.example.forkjoin.ForkJoin;
import org.example.threads.MultiThreadedSearch;
import org.example.unithread.UniThread;

public class Main {
    public static void main(String[] args) throws InterruptedException {
//        ForkJoin.executar();
//        MultiThreadedSearch.executar();
        UniThread.executar();
    }
}
