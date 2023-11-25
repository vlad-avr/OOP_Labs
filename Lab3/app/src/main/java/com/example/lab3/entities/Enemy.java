package com.example.lab3.entities;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;

import com.example.lab3.R;
import com.example.lab3.actions.EntityAction;
import com.example.lab3.graphics.GameDisplay;
import com.example.lab3.graphics.SingleSheet;
import com.example.lab3.graphics.Sprite;
import com.example.lab3.logic.Game;
import com.example.lab3.logic.PathFinding;
import com.example.lab3.mapping.MapHolder;

public class Enemy extends Entity{

    private double range;

    protected EntityAction action;
    protected PathFinding.Pair wanderDir = new PathFinding.Pair(0, 1);
    private int attackRange;
    private int speed;
    private double fumbleChance = 0.0;
    private double attackFailChance = 0.0;
    private boolean agroed = false;
    private Player player;

    public Enemy(Context context, MapHolder mapHolder, int posX, int posY, Sprite sprite, Player player){
        super(context, mapHolder, posX, posY);
        this.sprite = sprite;
        mapHolder.setTilePassable(mapPosX, mapPosY, false);
        mapHolder.getTile(mapPosX, mapPosY).setEnemy(this);
        this.action = new EntityAction(this);
        this.player = player;
    }

    public void setName(String name){
        action.setPrompt("Damage " + name);
    }

    public void setMaxHealth(int maxHealth){
        this.maxHealth = maxHealth;
        this.health = maxHealth;
    }

    public void setSpeed(int speed){
        this.speed = speed;
    }

    public void setFumbleChance(double fumbleChance){
        this.fumbleChance = fumbleChance;
    }

    public void setRange(double range){
        this.range = range;
    }

    public void setAttackRange(int attackRange){
        this.attackRange = attackRange;
    }

    public void setDamageDelt(int damageDelt){
        this.damageDelt = damageDelt;
    }

    public void setAttackFailChance(double chance){
        this.attackFailChance = chance;
    }
    @Override
    public void draw(Canvas canvas, GameDisplay gameDisplay) {
        sprite.draw(canvas, (int) gameDisplay.gameToDisplayCoordinatesX(positionY), (int)gameDisplay.gameToDisplayCoordinatesY(positionX));
    }

    @Override
    public void update() {
        super.update();
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
        double roll = Game.rnd.nextDouble();
        if(roll < fumbleChance){
            return;
        }
        if(!mapHolder.inBounds(x,y) || !mapHolder.tileIsPassable(x, y)){
            return;
        }
        mapHolder.setTilePassable(mapPosX, mapPosY, true);
        mapHolder.getTile(mapPosX, mapPosY).setEnemy(null);
        positionX = x * MapHolder.TILE_WIDTH_PIXELS;
        positionY = y * MapHolder.TILE_HEIGHT_PIXELS;
        mapPosX = x;
        mapPosY = y;
        mapHolder.setTilePassable(mapPosX, mapPosY, false);
        mapHolder.getTile(mapPosX, mapPosY).setEnemy(this);
    }

    protected void attack(){
        double roll = Game.rnd.nextDouble();
        if(roll > attackFailChance) {
            player.stackDamage(damageDelt);
        }
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

    public void removeFromMap(){
        mapHolder.getTile(mapPosX, mapPosY).setEnemy(null);
        mapHolder.getTile(mapPosX, mapPosY).setPassable(true);
    }

}
