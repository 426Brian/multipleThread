package com.multiThread.blockQueue;


import java.util.concurrent.*;

public class BlockQueueDemo {

    public static void main(String[] args) {

        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));

//        System.out.println(blockingQueue.add("d"));

        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
//        System.out.println(blockingQueue.remove());
//        System.out.println(blockingQueue.remove());

        String element = blockingQueue.element();
        System.out.println(element);


        BlockingQueue<String> synQueue = new SynchronousQueue<>();
        new Thread(()->{
            try{
                System.out.println(Thread.currentThread().getName()+"\t put1");
                synQueue.put("1");
                System.out.println(Thread.currentThread().getName()+"\t put2");
                synQueue.put("2");
                System.out.println(Thread.currentThread().getName()+"\t put3");
                synQueue.put("3");

            }catch (Exception e){
                e.printStackTrace();
            }
        }, "AA").start();


        new Thread(()->{
            try{
                TimeUnit.SECONDS.sleep(2);
                System.out.println(synQueue.take());

                TimeUnit.SECONDS.sleep(2);
                System.out.println(synQueue.take());

                TimeUnit.SECONDS.sleep(2);
                System.out.println(synQueue.take());


            }catch (Exception e){
                e.printStackTrace();
            }
        }, "BB").start();
    }
}
