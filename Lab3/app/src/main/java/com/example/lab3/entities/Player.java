package com.example.lab3.entities;

import android.content.Context;
import android.graphics.Canvas;

import androidx.core.content.ContextCompat;

import com.example.lab3.R;
import com.example.lab3.graphics.GameDisplay;
import com.example.lab3.mapping.MapHolder;

import java.util.Iterator;
import java.util.List;

public class Player extends GameObject{
    private int speed = 1;

    private int dirX, dirY = 0;
    public static final int MAX_HEALTH_POINTS = 5;
    //private HealthBar healthBar;
    private int healthPoints = MAX_HEALTH_POINTS;
    //private PlayerState playerState;

    public Player(Context context, MapHolder mapHolder) {
        super(context, ContextCompat.getColor(context, R.color.player), mapHolder.START_X, mapHolder.START_Y);
        //this.healthBar = new HealthBar(context, this);
        //this.playerState = new PlayerState(this);
    }

    public void update() {
        if(!(dirY == 0 && dirX == 0)) {
            positionX += dirX * MapHolder.TILE_WIDTH_PIXELS;
            positionY += dirY * MapHolder.TILE_HEIGHT_PIXELS;
            mapPosX += dirX;
            mapPosY += dirY;
            dirX = 0;
            dirY = 0;
        }
        //playerState.update();
    }

    public void move(int dirX, int dirY){
        int stepX = mapPosX + dirX;
        int stepY = mapPosY + dirY;
        if(stepX < MapHolder.WIDTH && stepY < MapHolder.HEIGHT && stepX >= 0 && stepY >= 0) {
            this.dirX = dirX;
            this.dirY = dirY;
        }
    }

    public void draw(Canvas canvas, GameDisplay gameDisplay) {
        //healthBar.draw(canvas, gameDisplay);
    }

    public int getHealthPoint() {
        return healthPoints;
    }

    public void setHealthPoint(int healthPoints) {
        // Only allow positive values
        if (healthPoints >= 0)
            this.healthPoints = healthPoints;
    }

    /*public PlayerState getPlayerState() {
        return playerState;
    }*/
}
