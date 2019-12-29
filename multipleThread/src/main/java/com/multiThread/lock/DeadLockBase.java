package com.multiThread.lock;

public class DeadLockBase {
    public static void main(String[] args) {
        StringBuffer sbf1 = new StringBuffer();
        StringBuffer sbf2 = new StringBuffer();


        new Thread(){
            @Override
            public void run() {
                synchronized (sbf1){
                    sbf1.append("a");
                    sbf2.append("1");


                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (sbf2){
                        sbf1.append("b");
                        sbf2.append("2");

                        System.out.println(sbf1);
                        System.out.println(sbf2);
                    }
                }
            }
        }.start();


        new Thread(){
            @Override
            public void run() {
                synchronized (sbf2){
                    sbf1.append("c");
                    sbf2.append("3");

                    synchronized (sbf1){
                        sbf1.append("d");
                        sbf2.append("4");

                        System.out.println(sbf1);
                        System.out.println(sbf2);
                    }
                }
            }
        }.start();
    }
}
