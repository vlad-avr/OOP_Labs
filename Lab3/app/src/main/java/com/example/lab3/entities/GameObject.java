package com.example.lab3.entities;

import android.graphics.Canvas;

import com.example.lab3.graphics.GameDisplay;
import com.example.lab3.graphics.Sprite;
import com.example.lab3.mapping.MapHolder;

public abstract class GameObject {
    protected int positionX, positionY = 0;
    protected int mapPosX, mapPosY = 0;
    protected Sprite sprite;


    public GameObject(int positionX, int positionY) {
        this.mapPosX = positionX;
        this.mapPosY = positionY;
        this.positionX = positionX * MapHolder.TILE_WIDTH_PIXELS;
        this.positionY = positionY * MapHolder.TILE_HEIGHT_PIXELS;
    }

    public double getPositionX() { return positionX; }
    public double getPositionY() { return positionY; }

    public int getMapPosX(){
        return this.mapPosX;
    }

    public int getMapPosY(){
        return this.mapPosY;
    }

    public abstract void draw(Canvas canvas, GameDisplay gameDisplay);
    public abstract void update();

    public static double getDistanceBetweenObjects(GameObject obj1, GameObject obj2) {
        return Math.sqrt(
                Math.pow(obj2.getMapPosX() - obj1.getMapPosX(), 2) +
                        Math.pow(obj2.getMapPosY() - obj1.getMapPosY(), 2)
        );
    }
}
