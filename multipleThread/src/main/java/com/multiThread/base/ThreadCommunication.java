package com.multiThread.base;

/**
 * wait(): 一旦执行此方法, 当前线程进入阻塞状态, 并释放同步监视器
 * notify(): 一旦执行此方法, 就会唤醒被wait()的 一个线程。如果有多个线程被wait(), 就唤醒优先级高的
 * notifyAll(): 一旦执行此方法, 就会唤醒所有被wait()的线程
 *
 * 1. 上述三个方法只能出现在同步代码块或者同步方法
 * 2. 三个方法的调用者必须是同步代码块或者同部方法的同步监视器, 否则出现 java.lang.IllegalMonitorStateException
 *
 *
 *  sleep() 和 wait()
 *  1. 相同点： 此方法可以使线程进入阻塞状态
 *  2. 不同： a. sleep() 是Thread 类中的静态方法, wait() 是Object 类中定义的
 *           b. sleep() 可以使用在任意需要的位置, wait() 必须是同步代码块或者同部方法中使用
 *           c. sleep() 不会释放同步监视器(锁), wait() 会释放同步监视器(锁)
 */
public class ThreadCommunication {
    public static void main(String[] args) {
        Number number = new Number();

        new Thread(number, "1").start();
        new Thread(number, "2").start();
    }
}

class Number implements Runnable{
    private int number = 1;

    @Override
    public void run() {
        while (true){
            synchronized (this){
                notify();
                if(number <= 100){
                    System.out.println(Thread.currentThread().getName()+" --> "+number);
                    number++;
                }else {
                    break;
                }

                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
