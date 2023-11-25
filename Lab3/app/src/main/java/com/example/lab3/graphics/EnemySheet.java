package com.example.lab3.graphics;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.example.lab3.R;

public class EnemySheet extends SpriteSheet{
    public static enum ENEMIES{
        GUARD,
        GOBLIN,
        ANGRY_TREE,
        BUSHMASTER,
        BUG,
        GOLEM,
        MAGE,
        ROCK_SPIDER,
        SLIME,
        SKULL_CRAB
    }

    public EnemySheet(Context context){
        super();
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled = false;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemies, bitmapOptions);
    }

    public Sprite getSprite(EnemySheet.ENEMIES enemyType){
        switch (enemyType){
            case GUARD:
                return getSpriteByIndex(0, 0);
            case GOBLIN:
                return getSpriteByIndex(0, 1);
            case ANGRY_TREE:
                return getSpriteByIndex(0, 2);
            case BUSHMASTER:
                return getSpriteByIndex(0, 3);
            case BUG:
                return getSpriteByIndex(0, 4);
            case GOLEM:
                return getSpriteByIndex(1, 0);
            case MAGE:
                return getSpriteByIndex(1, 1);
            case SLIME:
                return getSpriteByIndex(1, 2);
            case ROCK_SPIDER:
                return getSpriteByIndex(1, 3);
            case SKULL_CRAB:
                return getSpriteByIndex(1, 4);
            default:
                break;
        }
        return null;
    }

    public Sprite getSprite(int id){
        return  getSpriteByIndex(0, id);
    }
}
