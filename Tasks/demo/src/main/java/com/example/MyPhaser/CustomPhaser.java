package com.example.MyPhaser;

public class CustomPhaser {
    private int parties;
    private int arrived;

    public CustomPhaser(int parties) {
        this.parties = parties;
        this.arrived = 0;
    }

    public synchronized void arriveAndAwait() throws InterruptedException {
        arrived++;

        if (arrived < parties) {
            while (arrived < parties) {
                wait();
            }
        } else {
            notifyAll();
            arrived = 0;
        }
    }
}