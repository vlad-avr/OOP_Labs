package com.example.MSQueueTask5;

import java.util.concurrent.atomic.AtomicReference;

public class MichaelScottQueue<T> {
    private static class Node<T> {
        final T value;
        AtomicReference<Node<T>> next;

        Node(T value) {
            this.value = value;
            this.next = new AtomicReference<>(null);
        }
    }
    //pointer to Head node
    private AtomicReference<Node<T>> head;
    //pointer to Tail node
    private AtomicReference<Node<T>> tail;

    public MichaelScottQueue() {
        Node<T> sentinel = new Node<>(null);
        head = new AtomicReference<>(sentinel);
        tail = new AtomicReference<>(sentinel);
    }

    public void enqueue(T value) {
        Node<T> newNode = new Node<>(value);
        while (true) {
            Node<T> currentTail = tail.get();
            Node<T> next = currentTail.next.get();
            if (currentTail == tail.get()) {
                if (next != null) {
                    //advance tail
                    tail.compareAndSet(currentTail, next);
                } else {
                    //attempt to insert new node
                    if (currentTail.next.compareAndSet(null, newNode)) {
                        //set new tail
                        tail.compareAndSet(currentTail, newNode);
                        return;
                    }
                }
            }
        }
    }

    public T dequeue() {
        while (true) {
            Node<T> currentHead = head.get();
            Node<T> currentTail = tail.get();
            Node<T> first = currentHead.next.get();

            if (currentHead == head.get()) {
                if (currentHead == currentTail) {
                    if (first == null) {
                        //empty
                        return null;
                    }
                    //advance tail
                    tail.compareAndSet(currentTail, first);
                } else {
                    T value = first.value;
                    if (head.compareAndSet(currentHead, first)) {
                        return value;
                    }
                }
            }
        }
    }
}
