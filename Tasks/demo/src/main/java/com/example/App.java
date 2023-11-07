package com.example;

import java.security.SecureRandom;
import java.util.concurrent.BrokenBarrierException;

import com.example.CyclicBarrier.CyclicBarrier;
import com.example.ReflectionTask4.MyReflector;

public final class App {
    private App() {
    }

    public static void main(String[] args) {
        // MyReflector reflector = new MyReflector();
        // reflector.reflectClass(Thread.class);
        CyclicBarrier myBarrier = new CyclicBarrier(6, () -> {
            System.out.println("\nBarrier has been reached by all threads!\n");
        });
        SecureRandom rnd = new SecureRandom();
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
                        System.out.println("\nThread " + Thread.currentThread().getName() + " has breached the containment");
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
