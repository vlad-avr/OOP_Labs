package com.example.ReentrantLock;

public class MyReentrantLock {
    private boolean isLocked = false;
    private Thread lockingThread = null;
    private int lockCount = 0;

    public synchronized void lock() {
        Thread currentThread = Thread.currentThread();

        if (isLocked && lockingThread != currentThread) {
            while (isLocked) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        isLocked = true;
        lockingThread = currentThread;
        lockCount++;
    }

    public synchronized void unlock() {
        if (Thread.currentThread() != lockingThread) {
            throw new IllegalMonitorStateException("Calling thread has not locked this lock.");
        }

        lockCount--;

        if (lockCount == 0) {
            isLocked = false;
            lockingThread = null;
            notify();
        }
    }
}
