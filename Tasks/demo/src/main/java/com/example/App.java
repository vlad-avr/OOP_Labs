package com.example;

import java.security.SecureRandom;

import com.example.SkipList.LockFreeSkipList;

public final class App {
    private App() {
    }

    public static void main(String[] args) {
        SecureRandom rnd = new SecureRandom();
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
