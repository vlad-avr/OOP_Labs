package com.example.QueueTestTask5;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

import com.example.MSQueueTask5.MichaelScottQueue;

public class QueueTest {
    @Test
    public void testEnqueueDequeue() throws InterruptedException {
        int numThreads = 10;
        int numEnqueueOperations = 1000;

        MichaelScottQueue<Integer> queue = new MichaelScottQueue<>();
        CountDownLatch latch = new CountDownLatch(numThreads);
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);

        for (int i = 0; i < numThreads; i++) {
            executorService.submit(() -> {
                try {
                    for (int j = 0; j < numEnqueueOperations; j++) {
                        if (j % 2 == 0) {
                            queue.enqueue(j);
                        } else {
                            Integer value = queue.dequeue();
                            assertEquals(Integer.valueOf(j - 1), value);
                        }
                    }
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await(5, TimeUnit.SECONDS);
        executorService.shutdown();

        assertNull(queue.dequeue());
        assertEquals(0, queue.size());
    }
}
