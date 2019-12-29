package com.multiThread.produce;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProduceNew {
    public static void main(String[] args) {
        Var var = new Var();

       new Thread(()->{
           for (int i = 0; i < 5; i++) {
               var.add();
           }

       }, "AA").start();

        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                var.sub();
            }

        }, "BB").start();

        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                var.add();
            }

        }, "CC").start();

        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                var.sub();
            }

        }, "DD").start();
    }
}


class Var {
    int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void add() {
        lock.lock();
        try {
            while (number != 0) {
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName() + " --> " + number);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }

    public void sub() {
        lock.lock();
        try {
            while (number == 0) {
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName() + " --> " + number);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }

}























