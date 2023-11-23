package com.example.lab3.graphics;

import android.content.Context;
import android.graphics.BitmapFactory;


public class SingleSheet extends SpriteSheet{
    public SingleSheet(Context context, int resId){
        super();
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled = false;
        bitmap = BitmapFactory.decodeResource(context.getResources(), resId, bitmapOptions);
    }

    public Sprite getSprite(){
        return getSpriteByIndex(0,0);
    }
}
