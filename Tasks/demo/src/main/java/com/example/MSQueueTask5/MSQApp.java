package com.example.MSQueueTask5;

import java.security.SecureRandom;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;

import com.example.MyPhaser.CustomPhaser;

public class MSQApp {

    public static void main(String[] args) {
        int numThreads = 3;
        int numEnqueueOperations = 100;

        MichaelScottQueue<Integer> queue = new MichaelScottQueue<>();
        // CountDownLatch latch = new CountDownLatch(numThreads);
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
        CustomPhaser phaser = new CustomPhaser(numThreads + 1);
        SecureRandom rnd = new SecureRandom();

        for (int i = 0; i < numThreads; i++) {
            executorService.submit(() -> {
                try {
                    for (int j = 0; j < numEnqueueOperations; j++) {
                        Thread.sleep(rnd.nextInt(100));
                        if (j % 2 == 0) {
                            queue.enqueue(j);
                            System.out.println(Thread.currentThread().getName() + " enqueued : " + j);
                        } else {
                            Integer value = queue.dequeue();
                            System.out.println(Thread.currentThread().getName() + " dequeued : " + value);
                        }
                    }
                    phaser.arriveAndAwait();
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            });
        }

        try {
            phaser.arriveAndAwait();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        executorService.shutdown();

        System.out.println(queue.dequeue() == null);
        System.out.println(queue.size());
    }
}
