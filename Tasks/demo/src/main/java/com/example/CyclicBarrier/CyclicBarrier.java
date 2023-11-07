package com.example.CyclicBarrier;

import java.util.concurrent.BrokenBarrierException;

public class CyclicBarrier {
    private final int parties;
    private int count = 0;
    private boolean broken = false;
    private Runnable action;

    public CyclicBarrier(int parties) {
        assert (parties > 0);
        this.parties = parties;
    }

    public CyclicBarrier(int parties, Runnable action) {
        assert (parties > 0);
        this.parties = parties;
        this.action = action;
    }

    public synchronized void await() throws BrokenBarrierException, InterruptedException {
        if (broken) {
            throw new BrokenBarrierException();
        }
        count++;
        if (count == parties) {
            if (action != null) {
                action.run();
            }
            count = 0;
            notifyAll();
        } else {
            wait();
        }
    }

    public synchronized void breakBarrier() {
        broken = true;
        count = 0;
        notifyAll();
    }

    public synchronized boolean isBroken() {
        return this.broken;
    }

    public synchronized void reset() {
        count = 0;
        broken = false;
    }

    public synchronized void resetBroken() {
        count = 0;
        broken = false;
        notifyAll();
    }

    public int getParties() {
        return this.parties;
    }
}
