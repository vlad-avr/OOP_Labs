package com.example;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;

import com.example.CyclicBarrier.CyclicBarrier;
import com.example.ReentrantLock.MyReentrantLock;
import com.example.ReflectionTask4.MyReflector;
import com.example.SkipList.LockFreeSkipList;
import com.example.ThreadPool.MyThreadPool;

public final class App {
    private App() {
    }

    public static void main(String[] args) {
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
        // LockFreeSkipList<String> list = new LockFreeSkipList<>(4);
        // for (int i = 0; i < 5; i++) {
        // int count = i;
        // Thread thr = new Thread(() -> {
        // Thread.currentThread().setName("W " + count);
        // for (int j = 0; j < 10; j++) {
        // if (rnd.nextBoolean() || list.isEmpty()) {
        // list.add(Thread.currentThread().getName() + " s " + j);
        // } else {
        // list.remove(Thread.currentThread().getName() + " s " + rnd.nextInt(j));
        // }
        // list.print();
        // }
        // });
        // thr.start();
        // }
        
    }
}
