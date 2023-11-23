package com.example.lab3.entities;

import android.content.Context;
import android.graphics.Canvas;

import com.example.lab3.R;
import com.example.lab3.graphics.GameDisplay;
import com.example.lab3.graphics.SingleSheet;
import com.example.lab3.graphics.Sprite;
import com.example.lab3.mapping.MapHolder;

public class Player extends GameObject{
    private int speed = 1;
    private final MapHolder mapHolder;
    private final Sprite sprite;
    private int dirX, dirY = 0;
    public static final int MAX_HEALTH_POINTS = 5;
    //private HealthBar healthBar;
    private int healthPoints = MAX_HEALTH_POINTS;
    //private PlayerState playerState;

    public Player(Context context, MapHolder mapHolder) {
        super(mapHolder.START_X, mapHolder.START_Y);
        SingleSheet singleSheet = new SingleSheet(context, R.drawable.player);
        sprite = singleSheet.getSprite();
        this.mapHolder = mapHolder;
        mapHolder.setTilePassingSprite(mapPosX, mapPosY, sprite);
        //this.healthBar = new HealthBar(context, this);
        //this.playerState = new PlayerState(this);
    }

    public void update() {
        if(!(dirY == 0 && dirX == 0) && mapHolder.tileIsPassable(mapPosX + dirX, mapPosY + dirY)) {
            mapHolder.setTilePassingSprite(mapPosX, mapPosY, null);
            positionX += dirX * MapHolder.TILE_WIDTH_PIXELS;
            positionY += dirY * MapHolder.TILE_HEIGHT_PIXELS;
            mapPosX += dirX;
            mapPosY += dirY;
            mapHolder.setTilePassingSprite(mapPosX, mapPosY, sprite);
            dirX = 0;
            dirY = 0;
        }
        //playerState.update();
    }

    public void move(int dirX, int dirY){
        int stepX = mapPosX + dirX;
        int stepY = mapPosY + dirY;
        if(mapHolder.inBounds(stepX, stepY) && mapHolder.tileIsPassable(stepX, stepY)) {
            this.dirX = dirX;
            this.dirY = dirY;
        }
    }

    public void draw(Canvas canvas, GameDisplay gameDisplay) {
        sprite.draw(canvas, (int) gameDisplay.gameToDisplayCoordinatesX(positionX), (int)gameDisplay.gameToDisplayCoordinatesY(positionY));
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
