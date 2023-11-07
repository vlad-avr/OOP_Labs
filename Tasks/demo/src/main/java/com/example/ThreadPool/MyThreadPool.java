package com.example.ThreadPool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class MyThreadPool {
    private final WorkerThread[] workers;
    private final BlockingQueue<Runnable> taskQueue;

    public MyThreadPool(int poolSize) {
        this.workers = new WorkerThread[poolSize];
        taskQueue = new LinkedBlockingDeque<>();
        for (int i = 0; i < poolSize; i++) {
            workers[i] = new WorkerThread();
            workers[i].start();
        }
    }

    public void submit(Runnable task) {
        try {
            taskQueue.put(task);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    public void shutdown() {
        while (!taskQueue.isEmpty()) {
            continue;
        }
        for (WorkerThread worker : workers) {
            worker.done = true;
        }
    }

    private class WorkerThread extends Thread {
        public boolean done = false;
        public void run() {
            while (!done) {
                try {
                    Runnable task = taskQueue.take();
                    task.run();
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
