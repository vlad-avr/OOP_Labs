package com.example.oop_lab3;

import android.view.SurfaceHolder;

public class GameLoop extends Thread{

    private boolean isRunning = true;
    public GameLoop(Game game, SurfaceHolder surfaceHolder) {
    }

    public void startLoop() {
        isRunning = true;
        start();
    }

    @Override
    public void run(){
        super.run();
        while (isRunning){

        }
    }
}
