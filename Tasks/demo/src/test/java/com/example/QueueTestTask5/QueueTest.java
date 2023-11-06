package com.example.QueueTestTask5;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.example.MSQueueTask5.MichaelScottQueue;

public class QueueTest {
    @Test
    public void testString(){
        MichaelScottQueue<String> queue = new MichaelScottQueue<>();
        queue.enqueue("A");
        queue.enqueue("DDD");
        queue.enqueue("FADE");
        assertEquals(queue.dequeue(), "A");
        queue.enqueue("BC");
        assertEquals(queue.dequeue(), "DDD");
        assertEquals(queue.dequeue(), "FADE");
        assertEquals(queue.dequeue(), "BC");
    }

    @Test
    public void testDouble(){
        final double eps = 0.00001;
        MichaelScottQueue<Double> queue = new MichaelScottQueue<>();
        queue.enqueue(0.01);
        queue.enqueue(12.9);
        queue.enqueue(1233.8);
        assertEquals(queue.dequeue(), 0.01, eps);
        queue.enqueue(2.4);
        assertEquals(queue.dequeue(), 12.9, eps);
        assertEquals(queue.dequeue(), 1233.8, eps);
        assertEquals(queue.dequeue(), 2.4, eps);
    }

    @Test
    public void testInt(){
        MichaelScottQueue<Integer> queue = new MichaelScottQueue<>();
        queue.enqueue(0);
        queue.enqueue(1);
        queue.enqueue(12);
        assertEquals(queue.dequeue(), 0);
        queue.enqueue(4);
        assertEquals(queue.dequeue(), 1);
        assertEquals(queue.dequeue(), 12);
        assertEquals(queue.dequeue(), 4);
    }
}
