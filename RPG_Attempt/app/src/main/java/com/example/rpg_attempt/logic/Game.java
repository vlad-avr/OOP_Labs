package com.example.rpg_attempt.logic;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.example.rpg_attempt.graphics.GameDisplay;
import com.example.rpg_attempt.mapping.MapHolder;

import java.security.SecureRandom;

public class Game extends SurfaceView implements SurfaceHolder.Callback{

    private MainLoop loop;

    private GameDisplay gameDisplay;
    private MapHolder mapHolder;

    public Game(Context context) {
        super(context);

        // Get surface holder and add callback
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        mapHolder = new MapHolder(context);
        mapHolder.generateMapPlan();
        loop = new MainLoop(this, surfaceHolder);
        setFocusable(true);
    }


    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        loop.startLoop();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }
    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);

        // Draw Tilemap

        mapHolder.draw(canvas, gameDisplay);
    }

    public void update(){
        gameDisplay.update();
    }
}
