package com.example.oop_lab3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

public class Player {

    private float posX;
    private float posY;
    private float rad;
    private Paint playerPaint;

    public Player(Context context, float poxX, float poxY, float rad){
        this.playerPaint = new Paint();
        int color = ContextCompat.getColor(context, R.color.player);
        playerPaint.setColor(color);
        this.posX = posX;
        this.posY = posY;
        this.rad = rad;
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle(posX, posY, rad, playerPaint);
    }

    public void update(){

    }
}
