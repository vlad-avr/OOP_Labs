package com.example.rpg_attempt.graphics;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;


public abstract class SpriteSheet {
    protected static final int SPRITE_WIDTH_PIXELS = 64;
    protected static final int SPRITE_HEIGHT_PIXELS = 64;
    protected Bitmap bitmap;

    public SpriteSheet() {
        /*BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled = false;*/
        //bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.tiles, bitmapOptions);
    }
/*
    public Sprite[] getPlayerSpriteArray() {
        Sprite[] spriteArray = new Sprite[3];
        for(int i = 0; i < 5; i++){
            spriteArray[i] = new Sprite(this, new Rect(i*SPRITE_WIDTH_PIXELS, 0, (i+1)*SPRITE_WIDTH_PIXELS, SPRITE_HEIGHT_PIXELS))
        }
        return spriteArray;
    }
*/


    public Bitmap getBitmap() {
        return bitmap;
    }

    protected Sprite getSpriteByIndex(int idxRow, int idxCol) {
        return new Sprite(this, new Rect(
                idxCol*SPRITE_WIDTH_PIXELS,
                idxRow*SPRITE_HEIGHT_PIXELS,
                (idxCol + 1)*SPRITE_WIDTH_PIXELS,
                (idxRow + 1)*SPRITE_HEIGHT_PIXELS
        ));
    }

}
