package com.example.lab3.mapping;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.lab3.graphics.RockSheet;
import com.example.lab3.graphics.Sprite;
import com.example.lab3.graphics.TileSheet;

public class Tile {
    protected final Rect mapLocationRect;
    private final Sprite sprite;
    private Sprite addedSprite = null;

    public Tile(TileSheet spriteSheet, Rect mapLocationRect, Tile.TileType tileType) {
        this.mapLocationRect = mapLocationRect;
        sprite = spriteSheet.getSprite(tileType);
    }

    public void addRock(RockSheet rockSheet, RockSheet.ROCKS rockType) {
        addedSprite = rockSheet.getSprite(rockType);
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
    }
}
