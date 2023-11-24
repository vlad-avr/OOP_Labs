package com.example.lab3.entities;

import android.content.Context;
import android.graphics.Canvas;

import com.example.lab3.R;
import com.example.lab3.graphics.GameDisplay;
import com.example.lab3.graphics.SingleSheet;
import com.example.lab3.mapping.MapHolder;

public class Enemy extends Entity{

    public Enemy(Context context, MapHolder mapHolder, int maxHealth, int posX, int posY){
        super(context, mapHolder, maxHealth, posX, posY);
        SingleSheet singleSheet = new SingleSheet(context, R.drawable.player);
        sprite = singleSheet.getSprite();
        mapHolder.setTilePassable(mapPosX, mapPosY, false);
    }
    @Override
    public void draw(Canvas canvas, GameDisplay gameDisplay) {
        sprite.draw(canvas, (int) gameDisplay.gameToDisplayCoordinatesX(positionX), (int)gameDisplay.gameToDisplayCoordinatesY(positionY));
    }

    @Override
    public void update() {
        super.update();
    }
}
