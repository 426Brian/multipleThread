package com.multiThread.lock;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();

        for (int i = 1; i <= 5; i++) {
            int tmp = i;
            new Thread(() -> {
                myCache.put(tmp + "", tmp + "");
            }, String.valueOf(i)).start();
        }

        for (int i = 1; i <= 5; i++) {
            int tmp = i;
            new Thread(() -> {
                myCache.get(tmp + "");
            }, String.valueOf(i)).start();
        }
    }
}

class MyCache {
    private volatile Map<String, Object> map = new HashMap<String, Object>();

    private ReentrantReadWriteLock rwlock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {
        rwlock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 正在写入 " + key);
            TimeUnit.MICROSECONDS.sleep(300);
            map.put(key, value);

            System.out.println(Thread.currentThread().getName() + "\t 写入 " + key + " 完成");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rwlock.writeLock().unlock();
        }


    }

    public void get(String key) {

        rwlock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 正在读取 " + key);
            TimeUnit.MICROSECONDS.sleep(300);

            Object result = map.get(key);

            System.out.println(Thread.currentThread().getName() + "\t 读取 " + result + " 完成");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rwlock.readLock().unlock();
        }

    }
}