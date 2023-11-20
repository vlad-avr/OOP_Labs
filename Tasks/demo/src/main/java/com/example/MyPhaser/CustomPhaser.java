package com.example.MyPhaser;

public class CustomPhaser {
    private final int parties;
    private int arrived = 0;

    public CustomPhaser(int parties) {
        this.parties = parties;
    }

    public synchronized void arriveAndAwait() throws InterruptedException {
        arrived++;
        if (arrived < parties) {
            wait();
        } else {
            notifyAll();
            arrived = 0;
        }
    }

    public int getParties(){
        return this.parties;
    }
}