package com.multiThread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReenterLockDemo {
    public static void main(String[] args) {
        Phone phone = new Phone();

        new Thread(() -> {
            phone.sms();
        }, "AAA").start();

        new Thread(() -> {
            phone.sms();
        }, "BBB").start();


        try {
            Thread.sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("*********************************");
        new Thread(phone, "CCC").start();
        new Thread(phone, "DDD").start();
    }
}

class Phone implements Runnable {
    private Lock lock = new ReentrantLock();

    public synchronized void sms() {
        System.out.println(Thread.currentThread().getName() + "\t invoke sms()");
        smail();
    }

    public synchronized void smail() {
        System.out.println(Thread.currentThread().getName() + "\t invoke smail()");
    }

    @Override
    public void run() {
        get();
    }

    public void get() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t invoke get()");
            set();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void set() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t invoke set()");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
