package com.example.ReentrantLock;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class MyReentrantLockApp {

    private static SecureRandom rnd = new SecureRandom();
    public static void main(String[] args){
        MyReentrantLock rLock = new MyReentrantLock();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("String " + i);
        }
        for (int i = 0; i < 4; i++) {
            Thread thr = new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    rLock.lock();
                    System.out.println(Thread.currentThread().getName() + " has locked list to add a string");
                    list.add(Thread.currentThread().getName() + " S " + j);
                    if (rnd.nextBoolean()) {
                        rLock.lock();
                        System.out.println(
                                Thread.currentThread().getName() + " has locked list again to delete first string");
                        list.remove(0);
                        rLock.unlock();
                    }
                    System.out.println("\nList:");
                    for(int k = 0; k < list.size(); k++){
                        System.out.println(list.get(k));
                    }
                    System.out.println(Thread.currentThread().getName() + " has unlocked list");
                    rLock.unlock();
                }
            });
            thr.start();
        }
    }
}
