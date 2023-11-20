package com.example.ThreadPool;

import java.security.SecureRandom;

public class MyThreadPoolApp {

    public static void main(String[] args) {
        System.out.println("\n THREAD POOL WORKING : \n");
        MyThreadPool myPool = new MyThreadPool(4);
        SecureRandom rnd = new SecureRandom();
        for (int i = 0; i < 10; i++) {
            int counter = i + 1;
            myPool.submit(() -> {
                int t = rnd.nextInt(2000) + 1000;
                System.out.println(Thread.currentThread().getName() + " will be working on Task " + counter + " for "
                        + t + " milliseconds.");
                try {
                    Thread.sleep(t);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
                System.out.println(Thread.currentThread().getName() + " has finished working on Task " + counter);
            });
        }
        myPool.shutdown();

    }
}
