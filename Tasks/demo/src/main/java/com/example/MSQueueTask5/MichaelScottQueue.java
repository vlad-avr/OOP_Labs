package com.example.MSQueueTask5;

import java.util.concurrent.atomic.AtomicReference;

public class MichaelScottQueue<T> {
    private static class Node<T> {
        T value;
        AtomicReference<Node<T>> next;

        Node(T value) {
            this.value = value;
            this.next = new AtomicReference<>(null);
        }
    }

    private AtomicReference<Node<T>> head;
    private AtomicReference<Node<T>> tail;

    public MichaelScottQueue() {
        Node<T> dummy = new Node<>(null);
        this.head = new AtomicReference<>(dummy);
        this.tail = new AtomicReference<>(dummy);
    }

    public void enqueue(T value) {
        Node<T> newNode = new Node<>(value);

        while (true) {
            Node<T> currentTail = tail.get();
            Node<T> tailNext = currentTail.next.get();

            if (currentTail == tail.get()) {
                if (tailNext != null) {
                    tail.compareAndSet(currentTail, tailNext);
                } else {
                    if (currentTail.next.compareAndSet(null, newNode)) {
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
            Node<T> firstNode = currentHead.next.get();

            if (currentHead == head.get()) {
                if (currentHead == currentTail) {
                    if (firstNode == null) {
                        return null;
                    }
                    tail.compareAndSet(currentTail, firstNode);
                } else {
                    if (head.compareAndSet(currentHead, firstNode)) {
                        return firstNode.value;
                    }
                }
            }
        }
    }

    public int size() {
        int count = 0;
        Node<T> current = head.get().next.get();

        while (current != null) {
            count++;
            current = current.next.get();
        }

        return count;
    }
}