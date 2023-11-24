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
        String prompt = "";
        switch (rockType){
            case SMOL:
                prompt = "Break small rock";
                break;
            case BIG:
                passable = false;
                prompt = "Break big rock";
                break;
            case GOLD:
                passable = false;
                prompt = "Mine gold";
                break;
            case RUBY:
                passable = false;
                prompt = "Mine ruby";
                break;
            case MUSHROOM:
                passable = false;
                prompt = "Harvest shrooms";
                break;
        }
        addedObject = new StaticObject(rockSheet.getSprite(rockType), prompt);
        taken = true;
    }

    public void addPlant(PlantSheet plantSheet, PlantSheet.PLANTS plantType) {
        if(taken){return;}
        String prompt = "";
        switch (plantType){
            case SHROOM:
                prompt = "Harvest shroom";
                break;
            case BRIAR:
                prompt = "Cut briar";
                break;
            case PINE:
                prompt = "Chop pine";
                passable = false;
                break;
            case TREE:
                prompt = "Chomp tree";
                passable = false;
                break;
            case BUSH:
                prompt = "Harvest bush";
                passable = false;
                break;
        }
        addedObject = new StaticObject(plantSheet.getSprite(plantType), prompt);
        taken = true;
    }

    public boolean isPassable() {
        return this.passable;
    }

    public void setPassable(boolean passable){
        this.passable = passable;
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
