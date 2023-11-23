package com.example.lab3.graphics;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.example.lab3.R;

public class RockSheet extends SpriteSheet{

    public static enum ROCKS{
        SMOL,
        BIG,
        GOLD,
        RUBY,
        MUSHROOM
    }

    public RockSheet(Context context){
        super();
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled = false;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.rocks, bitmapOptions);
    }

    public Sprite getSprite(ROCKS rockType){
        switch (rockType){
            case SMOL:
                return getSpriteByIndex(0, 0);
            case BIG:
                return getSpriteByIndex(0, 1);
            case GOLD:
                return getSpriteByIndex(0, 2);
            case RUBY:
                return getSpriteByIndex(0, 3);
            case MUSHROOM:
                return getSpriteByIndex(0, 4);
            default:
                break;
        }
        return null;
    }
}
