package com.example.lab3.graphics;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;


public abstract class SpriteSheet {
    protected static final int SPRITE_WIDTH_PIXELS = 64;
    protected static final int SPRITE_HEIGHT_PIXELS = 64;
    protected Bitmap bitmap;

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
