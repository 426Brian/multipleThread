package com.multiThread.produce;

public class ProductTest {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        Producer p1 = new Producer(clerk);
        p1.setName("producer1");
        p1.start();

        Consumer c1 = new Consumer(clerk);
        c1.setName("customer1");
        c1.start();
    }
}

class Clerk {

    private int productCount = 0;

    // 生产
    public synchronized void produceProduct() {
        if(productCount < 20){
            productCount++;
            System.out.println(Thread.currentThread().getName()+" 开始生产第 "+productCount+" 个产品");
            notify();
        }else {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // 消费
    public synchronized void consumeProduct() {
        if(productCount > 0){
            System.out.println(Thread.currentThread().getName()+" 开始消费第 "+productCount+" 个产品");
            productCount--;
            notify();
        }else {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


class Producer extends Thread{

    private Clerk clerk;
    private String name;

    public Producer(Clerk clerk) {
        this.clerk = clerk;
    }


    @Override
    public void run() {
        System.out.println(getName()+" 开始生产产品......");

        while(true){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.produceProduct();
        }
    }


}

class Consumer extends Thread{
    private Clerk clerk;

    public Consumer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println(getName()+" 开始消费产品......");

        while(true){
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.consumeProduct();
        }

    }
}