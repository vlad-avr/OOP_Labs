package com.example.MyPhaser;

public class CustomPhaser {
    private int parties;
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

    public void addParty(){
        parties++;
    }

    public void removeParty(){
        if(parties >= 1){
            parties--;
        }
    }
}