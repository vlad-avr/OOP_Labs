package com.example.rpg_attempt.graphics;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.example.rpg_attempt.R;
import com.example.rpg_attempt.mapping.Tile;

public class TileSheet extends SpriteSheet{

    public TileSheet(Context context){
        super();
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled = false;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.tiles, bitmapOptions);
    }

    public Sprite getSprite(Tile.TileType tileType){
        switch (tileType){
            case GROUND:
                return getSpriteByIndex(0, 0);
            case ROCK:
                return getSpriteByIndex(0, 1);
            case SAND:
                return getSpriteByIndex(0, 2);
            case WATER:
                return getSpriteByIndex(0, 3);
            case WALL:
                return getSpriteByIndex(0, 4);
            default:
                break;
        }
        return null;
    }
}
