package com.multiThread.orderExecute;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintArray {
    public static void main(String[] args) {
        String[] str = {"a", "b", "c", "d", "e", "f"};
        Demo demo = new Demo();
        new Thread(() -> {
            demo.print(str);
        }).start();

        new Thread(() -> {
            demo.print(str);
        }).start();
    }
}

class Demo {
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private Condition condition2 = lock.newCondition();

    public void print(String[] str) {

        for (String strT : str) {

            lock.lock();
            while (number != 0){
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + " --> " + strT);
            lock.unlock();

        }

    }



}
