package com.example.oop_lab3.map;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.oop_lab3.graphics.Sprite;
import com.example.oop_lab3.graphics.SpriteSheet;

class LavaTile extends Tile {
    private final Sprite sprite;

    public LavaTile(SpriteSheet spriteSheet, Rect mapLocationRect) {
        super(mapLocationRect);
        sprite = spriteSheet.getLavaSprite();
    }

    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas, mapLocationRect.left, mapLocationRect.top);
    }
}