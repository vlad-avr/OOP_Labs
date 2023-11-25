package com.example.lab3.entities;

import android.content.Context;
import android.graphics.Canvas;

import com.example.lab3.graphics.GameDisplay;
import com.example.lab3.mapping.MapHolder;

public abstract class Entity extends GameObject{

    protected MapHolder mapHolder;
    protected Context context;
    protected int maxHealth;
    protected int health;
    protected int stackedDamage = 0;
    protected int damageDelt = 2;

    protected boolean isDead = false;

    public Entity(Context context, MapHolder mapHolder, int posX, int posY){
        super(posX, posY);
        this.context = context;
        this.mapHolder = mapHolder;
    }

    public void stackDamage(int damage){
        stackedDamage += damage;
    }

    public int getHealth(){
        return this.health;
    }

    private void takeDamage(){
        this.health -= Math.min(health, stackedDamage);
        if(health <= 0){
            isDead = true;
        }
        stackedDamage = 0;
    }
    @Override
    public void draw(Canvas canvas, GameDisplay gameDisplay) {
    }

    public boolean isDead(){
        return this.isDead;
    }


    @Override
    public void update() {

    }

    public void updateHealth(){
        takeDamage();
    }

    public int dealDamage() {
        return damageDelt;
    }

    public int getMaxHealth() {
        return maxHealth;
    }
}
