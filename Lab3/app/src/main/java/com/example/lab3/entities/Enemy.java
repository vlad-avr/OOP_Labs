package com.example.lab3.entities;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;

import com.example.lab3.R;
import com.example.lab3.graphics.GameDisplay;
import com.example.lab3.graphics.SingleSheet;
import com.example.lab3.logic.PathFinding;
import com.example.lab3.mapping.MapHolder;

public class Enemy extends Entity{

    private double range;
    private Player player;

    public Enemy(Context context, MapHolder mapHolder, int maxHealth, int posX, int posY, int range, Player player){
        super(context, mapHolder, maxHealth, posX, posY);
        SingleSheet singleSheet = new SingleSheet(context, R.drawable.player);
        sprite = singleSheet.getSprite();
        mapHolder.setTilePassable(mapPosX, mapPosY, false);
        this.range = range;
        this.player = player;
    }
    @Override
    public void draw(Canvas canvas, GameDisplay gameDisplay) {
        sprite.draw(canvas, (int) gameDisplay.gameToDisplayCoordinatesX(positionY), (int)gameDisplay.gameToDisplayCoordinatesY(positionX));
    }

    @Override
    public void update() {
        super.update();
        if(GameObject.getDistanceBetweenObjects(player, this) <= range){
            PathFinding.Pair playerPos = new PathFinding.Pair(player.getMapPosX(), player.getMapPosY());
            PathFinding.Pair stepPos = PathFinding.aStarSearch(mapHolder, new PathFinding.Pair(mapPosX, mapPosY), playerPos);
            if(stepPos != null) {
                if (stepPos.equals(playerPos)) {
                    Log.d("Enemy", "ATTACK ");
                } else {
                    move(stepPos.first, stepPos.second);
                }
            }
        }
    }

    private void move(int x, int y){
        mapHolder.setTilePassable(mapPosX, mapPosY, true);
        positionX += x * MapHolder.TILE_WIDTH_PIXELS;
        positionY += y * MapHolder.TILE_HEIGHT_PIXELS;
        mapPosX += x;
        mapPosY += y;
        mapHolder.setTilePassable(mapPosX, mapPosY, false);
    }
}
