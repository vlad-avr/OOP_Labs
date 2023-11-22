package com.example.oop_lab3.gameobject;

import android.content.Context;

import androidx.core.content.ContextCompat;

import com.example.oop_lab3.GameLoop;
import com.example.oop_lab3.R;

public class Bullet extends Circle {
    public static final double SPEED_PIXELS_PER_SECOND = 800.0;
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;

    public Bullet(Context context, double positionX, double positionY, double directionX, double directionY) {
        super(
                context,
                ContextCompat.getColor(context, R.color.spell),
                positionX,
                positionY,
                25
        );
        velocityX = directionX*MAX_SPEED;
        velocityY = directionY*MAX_SPEED;
    }

    @Override
    public void update() {
        positionX = positionX + velocityX;
        positionY = positionY + velocityY;
    }
}