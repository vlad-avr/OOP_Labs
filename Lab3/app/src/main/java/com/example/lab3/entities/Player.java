package com.example.lab3.entities;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;

import com.example.lab3.R;
import com.example.lab3.graphics.GameDisplay;
import com.example.lab3.graphics.SingleSheet;
import com.example.lab3.graphics.Sprite;
import com.example.lab3.logic.Game;
import com.example.lab3.mapping.MapHolder;

public class Player extends Entity{
    private int speed = 1;
    private int dirX, dirY = 0;
    //private PlayerState playerState;

    public Player(Context context, MapHolder mapHolder, int maxHealth) {
        super(context, mapHolder, maxHealth, mapHolder.START_X, mapHolder.START_Y);
        SingleSheet singleSheet = new SingleSheet(context, R.drawable.player);
        sprite = singleSheet.getSprite();
        mapHolder.setTilePassable(mapPosY, mapPosX, false);
        //this.healthBar = new HealthBar(context, this);
        //this.playerState = new PlayerState(this);
    }

    public void update() {
        super.update();
        if(!(dirY == 0 && dirX == 0) && mapHolder.tileIsPassable(mapPosY + dirY, mapPosX + dirX)) {
            mapHolder.setTilePassable(mapPosY, mapPosX, true);
            positionX += dirX * MapHolder.TILE_WIDTH_PIXELS;
            positionY += dirY * MapHolder.TILE_HEIGHT_PIXELS;
            mapPosX += dirX;
            mapPosY += dirY;
            mapHolder.setTilePassable(mapPosY, mapPosX, false);
            dirX = 0;
            dirY = 0;
        }
        //playerState.update();
    }

    public void move(int dirX, int dirY){
        int stepX = mapPosX + dirX;
        int stepY = mapPosY + dirY;
        if(mapHolder.inBounds(stepY, stepX) && mapHolder.tileIsPassable(stepY, stepX)) {
            this.dirX = dirX;
            this.dirY = dirY;
            Game.sendUpdateRequest();
        }
    }

    public void draw(Canvas canvas, GameDisplay gameDisplay) {
        sprite.draw(canvas, (int) gameDisplay.gameToDisplayCoordinatesX(positionX), (int)gameDisplay.gameToDisplayCoordinatesY(positionY));
        //healthBar.draw(canvas, gameDisplay);
    }


    /*public PlayerState getPlayerState() {
        return playerState;
    }*/
}
