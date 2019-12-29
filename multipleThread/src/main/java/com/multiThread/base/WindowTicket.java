package com.multiThread.base;

import java.util.concurrent.locks.ReentrantLock;

/**
 * synchronized 与 lock
 * 相同： 两者都可以解决线程安全问题
 * 不同：
 */
public class WindowTicket {
    public static void main(String[] args) {
        Window window = new Window();

        new Thread(window, "window1").start();
        new Thread(window, "window2").start();
        new Thread(window, "window3").start();


    }

}

class Window implements Runnable {
    private int ticket = 100;

    private ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while(true){

            try {

                Thread.sleep(100);
                lock.lock();
                if(ticket > 0){
                    System.out.println(Thread.currentThread().getName()+" --> "+(--ticket));
                }else {
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }

        }
    }

    /*@Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (this){
                if (ticket > 0) {
                    System.out.println(Thread.currentThread().getName() + " --> " + (--ticket));
                } else {
                    break;
                }
            }

        }
    }*/
}