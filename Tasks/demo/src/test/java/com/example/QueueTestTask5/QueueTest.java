package com.example.QueueTestTask5;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.security.SecureRandom;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

import com.example.MSQueueTask5.MichaelScottQueue;
import com.example.MyPhaser.CustomPhaser;

public class QueueTest {
    @Test
    public void testEnqueueDequeue() throws InterruptedException {
        int numThreads = 1;
        int numEnqueueOperations = 100;

        MichaelScottQueue<Integer> queue = new MichaelScottQueue<>();
        //CountDownLatch latch = new CountDownLatch(numThreads);
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
        CustomPhaser phaser = new CustomPhaser(numThreads+1);
        SecureRandom rnd = new SecureRandom();

        for (int i = 0; i < numThreads; i++) {
            executorService.submit(() -> {
                try {
                    for (int j = 0; j < numEnqueueOperations; j++) {
                        Thread.sleep(rnd.nextInt(100));
                        if (j % 2 == 0) {
                            System.out.println(j);
                            queue.enqueue(j);
                            System.out.println(queue.size());
                        } else {
                            Integer value = queue.dequeue();
                            assertEquals(Integer.valueOf(2), value);
                        }
                    }
                    phaser.arriveAndAwait();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            });
        }

        phaser.arriveAndAwait();
        executorService.shutdown();

        assertNull(queue.dequeue());
        assertEquals(0, queue.size());
    }
}
