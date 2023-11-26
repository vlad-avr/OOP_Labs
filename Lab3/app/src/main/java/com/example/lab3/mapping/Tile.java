package com.example.lab3.mapping;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

import com.example.lab3.entities.Enemy;
import com.example.lab3.graphics.PlantSheet;
import com.example.lab3.graphics.RockSheet;
import com.example.lab3.graphics.Sprite;
import com.example.lab3.graphics.TileSheet;

public class Tile {
    protected final Rect mapLocationRect;
    private final Sprite sprite;
    //private Sprite addedSprite = null;
   // private Sprite passingSprite = null;
    private StaticObject addedObject = null;
    private Enemy enemy = null;
    private boolean passable = true;
    private boolean taken = false;
    private final TileType tileType;


    public Tile(TileSheet spriteSheet, Rect mapLocationRect, Tile.TileType tileType) {
        this.mapLocationRect = mapLocationRect;
        sprite = spriteSheet.getSprite(tileType);
        this.tileType = tileType;
    }

    public TileType getTileType(){
        return tileType;
    }

 /*   public void setPassingSprite(Sprite sprite){
        passingSprite = sprite;
    }
*/
    public void addRock(RockSheet rockSheet, RockSheet.ROCKS rockType) {
        if(taken){return;}
        switch (rockType){
            case BIG:
            case GOLD:
            case RUBY:
            case MUSHROOM:
                passable = false;
                break;
        }
        addedObject = StaticObjectFactory.makeRock(rockType, rockSheet);
        addedObject.setTile(this);
        taken = true;
    }

    public void addPlant(PlantSheet plantSheet, PlantSheet.PLANTS plantType) {
        if(taken){return;}
        switch (plantType){
            case PINE:
            case TREE:
            case BUSH:
                passable = false;
                break;
        }
        addedObject = StaticObjectFactory.makePlant(plantType, plantSheet);
        addedObject.setTile(this);
        taken = true;
    }

    public boolean isPassable() {
        return this.passable;
    }

    public void setPassable(boolean passable){
        this.passable = passable;
    }

    public void removeObj() {
        addedObject = null;
        taken = false;
        if(!passable){
            passable = true;
        }
    }

    public static enum TileType {
        GROUND,
        ROCK,
        SAND,
        WATER,
        WALL
    }

    public void draw(Canvas canvas) {
        sprite.draw(canvas, mapLocationRect.left, mapLocationRect.top);
        if(addedObject != null){
            addedObject.getSprite().draw(canvas, mapLocationRect.left, mapLocationRect.top);
        }
    }

    public StaticObject getObject(){
        return this.addedObject;
    }

    public Enemy getEnemy(){
        return this.enemy;
    }

    public void setEnemy(Enemy enemy){
        this.enemy = enemy;
    }
}
