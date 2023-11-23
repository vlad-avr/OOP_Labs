package com.example.lab3.logic;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.example.lab3.entities.Player;
import com.example.lab3.graphics.GameDisplay;
import com.example.lab3.mapping.MapHolder;

import java.security.SecureRandom;

public class Game extends SurfaceView implements SurfaceHolder.Callback{

    private MainLoop loop;

    private GameDisplay gameDisplay;
    private Player player;
    private MapHolder mapHolder;
/*
    private int displayX = 0;
    private int displayY = 0;*/

    public Game(Context context) {
        super(context);

        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        // Initialize display and center it around the player
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        mapHolder = new MapHolder(context);
        mapHolder.generateMapPlan();
        player = new Player(context, mapHolder);
        gameDisplay = new GameDisplay(displayMetrics.widthPixels, displayMetrics.heightPixels, player);
        loop = new MainLoop(this, surfaceHolder);
        setFocusable(true);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                /*displayX = (int)event.getX();
                displayY = (int)event.getY();*/
                return true;
            default:
                return true;
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
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
        player.draw(canvas, gameDisplay);
        mapHolder.draw(canvas, gameDisplay);
    }

    public void update(){
        gameDisplay.update();
    }
}
