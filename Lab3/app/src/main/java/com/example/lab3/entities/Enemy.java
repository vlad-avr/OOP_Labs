package com.example.lab3.entities;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;

import com.example.lab3.R;
import com.example.lab3.actions.EntityAction;
import com.example.lab3.graphics.GameDisplay;
import com.example.lab3.graphics.SingleSheet;
import com.example.lab3.logic.Game;
import com.example.lab3.logic.PathFinding;
import com.example.lab3.mapping.MapHolder;

public class Enemy extends Entity{

    private double range;

    protected EntityAction action;
    protected PathFinding.Pair wanderDir = new PathFinding.Pair(0, 1);
    private int attackRange;
    private int speed;
    private Player player;
    private boolean agroed = false;

    public Enemy(Context context, MapHolder mapHolder, int maxHealth, int posX, int posY, int range, int attackRange,int speed, Player player){
        super(context, mapHolder, maxHealth, posX, posY);
        SingleSheet singleSheet = new SingleSheet(context, R.drawable.player);
        sprite = singleSheet.getSprite();
        mapHolder.setTilePassable(mapPosX, mapPosY, false);
        this.range = range;
        this.attackRange = attackRange;
        this.player = player;
        this.action = new EntityAction(this);
    }
    @Override
    public void draw(Canvas canvas, GameDisplay gameDisplay) {
        sprite.draw(canvas, (int) gameDisplay.gameToDisplayCoordinatesX(positionY), (int)gameDisplay.gameToDisplayCoordinatesY(positionX));
    }

    @Override
    public void update() {
        super.updateHealth();
        Log.d("Enemy", "health: " + health);
        if(agroed){
            if (GameObject.getMapDistBetweenObjects(player, this) <= attackRange) {
                Log.d("Enemy", "ATTACK ");
                attack();
            }else {
                PathFinding.Pair playerPos = new PathFinding.Pair(player.getMapPosX(), player.getMapPosY());
                PathFinding.Pair stepPos = PathFinding.aStarSearch(mapHolder, new PathFinding.Pair(mapPosX, mapPosY), playerPos, speed);
                if (stepPos != null) {
                    move(stepPos.first, stepPos.second);
                }
            }
        }else{
            wanderDir = getRandMove();
            move(mapPosX + wanderDir.first, mapPosY + wanderDir.second);
        }
        if(GameObject.getDistanceBetweenObjects(player, this) <= range){
            agroed = true;
        }else{
            agroed = false;
        }
    }

    protected void move(int x, int y){
        if(!mapHolder.inBounds(x,y) || !mapHolder.tileIsPassable(x, y)){
            return;
        }
        mapHolder.setTilePassable(mapPosX, mapPosY, true);
        positionX = x * MapHolder.TILE_WIDTH_PIXELS;
        positionY = y * MapHolder.TILE_HEIGHT_PIXELS;
        mapPosX = x;
        mapPosY = y;
        mapHolder.setTilePassable(mapPosX, mapPosY, false);
    }

    protected void attack(){
        player.stackDamage(damageDelt);
    }

    protected PathFinding.Pair getRandMove(){
        if(Game.rnd.nextBoolean()){
            int dir = Game.rnd.nextInt(4);
            switch (dir){
                case 0:
                    return new PathFinding.Pair(0, 1);
                case 1:
                    return new PathFinding.Pair(1, 0);
                case 2:
                    return new PathFinding.Pair(0, -1);
                case 3:
                    return new PathFinding.Pair(-1, 0);
            }
        }
        return wanderDir;
    }

}
