package com.example.MSQueueTask5;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class MichaelScottQueue<T> {
    private static class Node<T> {
        private final T value;
        AtomicReference<Node<T>> next;
        Node(T value) {
            this.value = value;
            this.next = new AtomicReference<>(null);
        }
    }
    private final AtomicReference<Node<T>> head;
    private final AtomicReference<Node<T>> tail;
    public MichaelScottQueue() {
        Node<T> emptyNode = new Node<>(null);
        head = new AtomicReference<>(emptyNode);
        tail = new AtomicReference<>(emptyNode);
    }

    public void enqueue(T item) {
        Node<T> newNode = new Node<>(item);
        while (true) {
            Node<T> last = tail.get();
            Node<T> next = last.next.get();
            if (last == tail.get()) {
                if (next == null) {
                    if (last.next.compareAndSet(null, newNode)) {
                        tail.compareAndSet(last, newNode);
                        return;
                    }
                } else {
                    tail.compareAndSet(last, next);
                }
            }
        }
    }

    public T dequeue() {
        while (true) {
            Node<T> first = head.get();
            Node<T> last = tail.get();
            Node<T> next = first.next.get();
            if (first == head.get()) {
                if (first == last) {
                    if (next == null) {
                        return null;
                    }
                    tail.compareAndSet(last, next);
                } else {
                    T value = next.value;
                    if (head.compareAndSet(first, next)) {
                        return value;
                    }
                }
            }
        }
    }
    
    public int size(){
        int len = 0;
        Node<T> first = head.get();
        Node<T> last = tail.get();
        while (first != last) {
            if(first == null){
                return len;
            }
            len++;
            first = first.next.get();
        }
        return len;
    }
}