package com.example.lab3.mapping;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

import com.example.lab3.graphics.PlantSheet;
import com.example.lab3.graphics.RockSheet;
import com.example.lab3.graphics.Sprite;
import com.example.lab3.graphics.TileSheet;

public class Tile {
    protected final Rect mapLocationRect;
    private final Sprite sprite;
    private Sprite addedSprite = null;
   // private Sprite passingSprite = null;
    private boolean passable = true;
    private boolean taken = false;
    private final TileType tileType;

    //DEBUG
    private int obj_ind = -1;

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
        addedSprite = rockSheet.getSprite(rockType);
        switch (rockType){
            case BIG:
                obj_ind = 0;
                passable = false;
                break;
            case GOLD:
                obj_ind = 1;
                passable = false;
                break;
            case RUBY:
                obj_ind = 2;
                passable = false;
                break;
            case MUSHROOM:
                obj_ind = 3;
                passable = false;
                break;
        }
        taken = true;
    }

    public void addPlant(PlantSheet plantSheet, PlantSheet.PLANTS plantType) {
        if(taken){return;}
        addedSprite = plantSheet.getSprite(plantType);
        switch (plantType){
            case PINE:
                obj_ind = 10;
                passable = false;
                break;
            case TREE:
                obj_ind = 20;
                passable = false;
                break;
            case BUSH:
                obj_ind = 30;
                passable = false;
                break;
        }
        taken = true;
    }

    public boolean isPassable() {
        if(!this.passable){
            Log.d("TILE", "tile type : " + this.tileType + " object type " + obj_ind);
        }
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
/*
    public static Tile getTile(int idxTileType, SpriteSheet spriteSheet, Rect mapLocationRect) {

        switch(TileType.values()[idxTileType]) {

            case WATER_TILE:
                return new WaterTile(spriteSheet, mapLocationRect);
            case LAVA_TILE:
                return new LavaTile(spriteSheet, mapLocationRect);
            case GROUND_TILE:
                return new GroundTile(spriteSheet, mapLocationRect);
            case GRASS_TILE:
                return new GrassTile(spriteSheet, mapLocationRect);
            case TREE_TILE:
                return new TreeTile(spriteSheet, mapLocationRect);
            default:
                return null;
        }

    }*/

    public void draw(Canvas canvas) {
        sprite.draw(canvas, mapLocationRect.left, mapLocationRect.top);
        if(addedSprite != null){
            addedSprite.draw(canvas, mapLocationRect.left, mapLocationRect.top);
        }
        /*if(passingSprite != null){
            passingSprite.draw(canvas, mapLocationRect.left, mapLocationRect.top);
        }*/
    }
}
