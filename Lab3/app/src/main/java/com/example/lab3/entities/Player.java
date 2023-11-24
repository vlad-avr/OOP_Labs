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

    private boolean turnTaken = false;
    //private PlayerState playerState;

    public Player(Context context, MapHolder mapHolder,int startX, int startY,  int maxHealth) {
        super(context, mapHolder, maxHealth, startX, startY);
        SingleSheet singleSheet = new SingleSheet(context, R.drawable.player);
        sprite = singleSheet.getSprite();
        mapHolder.setTilePassable(mapPosX, mapPosY, false);
        //this.healthBar = new HealthBar(context, this);
        //this.playerState = new PlayerState(this);
    }

    @Override
    public void update() {
        if(!(dirY == 0 && dirX == 0) && mapHolder.tileIsPassable(mapPosX + dirX, mapPosY + dirY)) {
            mapHolder.setTilePassable(mapPosX, mapPosY, true);
            positionX += dirX * MapHolder.TILE_WIDTH_PIXELS;
            positionY += dirY * MapHolder.TILE_HEIGHT_PIXELS;
            mapPosX += dirX;
            mapPosY += dirY;
            mapHolder.setTilePassable(mapPosX, mapPosY, false);
            dirX = 0;
            dirY = 0;
            turnTaken = true;
        }
        if(turnTaken){
            Game.sendUpdateRequest();
            turnTaken = false;
        }
        //playerState.update();
    }

    @Override
    public void updateHealth(){
        super.updateHealth();
        Log.d("PLAYER", "updateHealth: " + health);
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
        sprite.draw(canvas, (int) gameDisplay.gameToDisplayCoordinatesX(positionY), (int)gameDisplay.gameToDisplayCoordinatesY(positionX));
        //healthBar.draw(canvas, gameDisplay);
    }


    /*public PlayerState getPlayerState() {
        return playerState;
    }*/
}
