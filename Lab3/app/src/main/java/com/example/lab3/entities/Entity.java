package com.example.lab3.entities;

import android.content.Context;
import android.graphics.Canvas;

import com.example.lab3.graphics.GameDisplay;
import com.example.lab3.mapping.MapHolder;

public class Entity extends GameObject{

    protected MapHolder mapHolder;
    protected Context context;
    private final int maxHealth;
    private int health;

    public Entity(Context context, MapHolder mapHolder, int maxHealth, int posX, int posY){
        super(posX, posY);
        this.maxHealth = maxHealth;
        this.health = this.maxHealth;
        this.context = context;
        this.mapHolder = mapHolder;
    }
    @Override
    public void draw(Canvas canvas, GameDisplay gameDisplay) {

    }

    @Override
    public void update() {

    }
}
