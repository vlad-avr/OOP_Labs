package com.example.MyPhaser;

import java.security.SecureRandom;

public class CustomPhaserApp {

    private static SecureRandom rnd = new SecureRandom();
    public static void main(String[] args) {

        System.out.println("\n PHASER WORKING : \n");
        CustomPhaser myPhaser = new CustomPhaser(6);

        for (int j = 0; j < 3; j++) {
            System.out.println("\n Barrier reset - starting all over again \n");
            for (int i = 0; i < myPhaser.getParties() - 1; i++) {
                Thread thr = new Thread(() -> {
                    try {
                        int t = rnd.nextInt(5000) + 1000;
                        System.out.println("\nThread " + Thread.currentThread().getName() + " will do something for "
                                + t + " milliseconds");
                        Thread.sleep(t);
                        System.out.println("\nThread " + Thread.currentThread().getName() + " has finished its work");
                        myPhaser.arriveAndAwait();
                        System.out.println(
                                "\nThread " + Thread.currentThread().getName() + " has breached the containment");
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                    }
                });
                thr.start();
            }
            try {
                myPhaser.arriveAndAwait();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
