package com.example.lab3.inventory;

public class Weapon extends Item{
    public final int damage;
    public final int piercing;
    public final boolean canChop;
    public final boolean canMine;

    public Weapon(int damage, int piercing, boolean canChop, boolean canMine){
        this.canChop = canChop;
        this.canMine = canMine;
        this.damage = damage;
        this.piercing = piercing;
    }
}
