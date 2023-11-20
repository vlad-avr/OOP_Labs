package com.example.CyclicBarrier;

import java.security.SecureRandom;
import java.util.concurrent.BrokenBarrierException;

public class CyclicBarrierApp {

    private static SecureRandom rnd = new SecureRandom();

    public static void main(String[] args) {

        System.out.println("\n CYCLIC BARRIER WORKING : \n");
        CyclicBarrier myBarrier = new CyclicBarrier(6, () -> {
            System.out.println("\nBarrier has been reached by all threads!\n");
        });

        for (int j = 0; j < 3; j++) {
            System.out.println("\n Barrier reset - starting all over again \n");
            for (int i = 0; i < myBarrier.getParties() - 1; i++) {
                Thread thr = new Thread(() -> {
                    try {
                        int t = rnd.nextInt(5000) + 1000;
                        System.out.println("\nThread " + Thread.currentThread().getName() + " will do something for "
                                + t + " milliseconds");
                        Thread.sleep(t);
                        System.out.println("\nThread " + Thread.currentThread().getName() + " has finished its work");
                        myBarrier.await();
                        System.out.println(
                                "\nThread " + Thread.currentThread().getName() + " has breached the containment");
                    } catch (InterruptedException | BrokenBarrierException e) {
                        System.out.println(e.getMessage());
                    }
                });
                thr.start();
            }
            try {
                myBarrier.await();
            } catch (BrokenBarrierException | InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
