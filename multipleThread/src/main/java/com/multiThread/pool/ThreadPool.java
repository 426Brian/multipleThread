package com.multiThread.pool;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {
    String str = new String("abcdeg");
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        executorService.execute(new NumberThread());

    }


}


class NumberThread implements Runnable{
    @Override
    public void run() {
        for(int i = 1; i <= 100; i++){
            if(i %2 == 0){
                System.out.println(Thread.currentThread().getName()+" --> "+i);
            }
        }
    }
}