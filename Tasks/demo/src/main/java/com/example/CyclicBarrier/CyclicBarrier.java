package com.example.CyclicBarrier;

public class CyclicBarrier {
    private final int parties;
    private int count = 0;
    private Runnable action;

    public CyclicBarrier(int parties){
        this.parties = parties;
    }

    public CyclicBarrier(int parties, Runnable action){
        this.parties = parties;
        this.action = action;
    }
}
