package com.multiThread.base;

public class AcountTest {
    public static void main(String[] args) {
        Acount acount = new Acount();

        Cutomer cutomer1 = new Cutomer(acount);
        Cutomer cutomer2 = new Cutomer(acount);

        cutomer1.start();
        cutomer2.start();
    }

}

class Acount {

    private double balance;

    public Acount() {

    }

    public Acount(double balance) {
        this.balance = balance;
    }

    public void deposit(double amt) {

        if (amt > 0) {
            synchronized (this) {
                balance += amt;

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "存钱, 余额： " + balance);
            }
        }
    }

}

class Cutomer extends Thread {
    private Acount acount;

    public Cutomer() {
    }

    public Cutomer(Acount acount) {
        this.acount = acount;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            acount.deposit(1000);
        }
    }
}

