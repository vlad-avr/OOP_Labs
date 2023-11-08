package com.example;

import java.security.SecureRandom;
import java.util.concurrent.BrokenBarrierException;

import com.example.CyclicBarrier.CyclicBarrier;
import com.example.ReflectionTask4.MyReflector;
import com.example.SkipList.LockFreeSkipList;
import com.example.ThreadPool.MyThreadPool;

public final class App {
    private App() {
    }

    public static void main(String[] args) {
        // System.out.println("\n REFLECT WORKING : \n");
        // MyReflector reflector = new MyReflector();
        // reflector.reflectClass(Thread.class);
        // System.out.println("\n CYCLIC BARRIER WORKING : \n");
        // CyclicBarrier myBarrier = new CyclicBarrier(6, () -> {
        // System.out.println("\nBarrier has been reached by all threads!\n");
        // });
        SecureRandom rnd = new SecureRandom();
        // for (int j = 0; j < 3; j++) {
        // System.out.println("\n Barrier reset - starting all over again \n");
        // for (int i = 0; i < myBarrier.getParties() - 1; i++) {
        // Thread thr = new Thread(() -> {
        // try {
        // int t = rnd.nextInt(5000) + 1000;
        // System.out.println("\nThread " + Thread.currentThread().getName() + " will do
        // something for "
        // + t + " milliseconds");
        // Thread.sleep(t);
        // System.out.println("\nThread " + Thread.currentThread().getName() + " has
        // finished its work");
        // myBarrier.await();
        // System.out.println("\nThread " + Thread.currentThread().getName() + " has
        // breached the containment");
        // } catch (InterruptedException | BrokenBarrierException e) {
        // System.out.println(e.getMessage());
        // }
        // });
        // thr.start();
        // }
        // try {
        // myBarrier.await();
        // } catch (BrokenBarrierException | InterruptedException e) {
        // System.out.println(e.getMessage());
        // }
        // }
        // System.out.println("\n THREAD POOL WORKING : \n");
        // MyThreadPool myPool = new MyThreadPool(4);
        // for(int i = 0; i < 10; i++){
        // int counter = i+1;
        // myPool.submit(() -> {
        // int t = rnd.nextInt(2000) + 1000;
        // System.out.println(Thread.currentThread().getName() + " will be working on
        // Task " + counter + " for " + t + " milliseconds.");
        // try {
        // Thread.sleep(t);
        // } catch (InterruptedException e) {
        // System.out.println(e.getMessage());
        // }
        // System.out.println(Thread.currentThread().getName() + " has finished working
        // on Task " + counter);
        // });
        // }
        // myPool.shutdown();
        LockFreeSkipList<String> list = new LockFreeSkipList<>(4);
        for (int i = 0; i < 5; i++) {
            int count = i;
            Thread thr = new Thread(() -> {
                Thread.currentThread().setName("W " + count);
                for (int j = 0; j < 10; j++) {
                    if (rnd.nextBoolean() || list.isEmpty()) {
                        list.add(Thread.currentThread().getName() + " s " + j);
                    } else {
                        list.remove(Thread.currentThread().getName() + " s " + rnd.nextInt(j));
                    }
                    list.print();
                }
            });
            thr.start();
        }
    }
}
