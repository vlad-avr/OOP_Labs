package com.example.lab3.graphics;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.example.lab3.R;

public class PlantSheet extends SpriteSheet{

    public static enum PLANTS{
        PINE,
        TREE,
        SHROOM,
        BUSH,
        BRIAR
    }

    public PlantSheet(Context context){
        super();
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled = false;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.plants, bitmapOptions);
    }

    public Sprite getSprite(PLANTS plantType){
        switch (plantType){
            case PINE:
                return getSpriteByIndex(0, 0);
            case TREE:
                return getSpriteByIndex(0, 1);
            case SHROOM:
                return getSpriteByIndex(0, 2);
            case BUSH:
                return getSpriteByIndex(0, 3);
            case BRIAR:
                return getSpriteByIndex(0, 4);
            default:
                break;
        }
        return null;
    }
}
