package com.example.lab3.logic;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.example.lab3.R;
import com.example.lab3.entities.Player;
import com.example.lab3.graphics.GameDisplay;
import com.example.lab3.mapping.MapHolder;

import java.security.SecureRandom;

public class Game extends SurfaceView implements SurfaceHolder.Callback{

    public static final SecureRandom rnd = new SecureRandom();
    private MainLoop loop;

    private GameDisplay gameDisplay;
    private Player player;
    private MapHolder mapHolder;
    private final int displayX;
    private final int displayY;

    public Game(Context context) {
        super(context);
        displayX = this.getResources().getDisplayMetrics().widthPixels;
        displayY = this.getResources().getDisplayMetrics().heightPixels;
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        // Initialize display and center it around the player
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        mapHolder = new MapHolder(context);
        mapHolder.generateMapPlan();
        player = new Player(context, mapHolder, 10);
        gameDisplay = new GameDisplay(displayMetrics.widthPixels, displayMetrics.heightPixels, player);
        loop = new MainLoop(this, surfaceHolder);
        setFocusable(true);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                int touchX = (int)event.getX();
                int touchY = (int)event.getY();
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
        mapHolder.draw(canvas, gameDisplay);
        player.draw(canvas, gameDisplay);
    }

    public void update(){
        gameDisplay.update();
        player.update();
    }

    public Player getPlayer() {
        return player;
    }
}
