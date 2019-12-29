package com.multiThread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MyThreadPoolDemo {

    public static void main(String[] args) {
        // 5 个线程
//        ExecutorService threadPool = Executors.newFixedThreadPool(5);

//        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        ExecutorService threadPool = Executors.newCachedThreadPool();
        try {
            // 模拟10 个用户办理业务
            for (int i = 0; i < 20; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });

//                TimeUnit.MICROSECONDS.sleep(200);
            }
        } catch (Exception e) {

            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }

    }
}
