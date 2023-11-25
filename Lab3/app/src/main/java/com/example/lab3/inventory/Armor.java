package com.example.lab3.inventory;

public class Armor extends Item{
    public final int protection;
    public final double ergonomic;

    public Armor(int protection, double ergonomic){
        this.protection = protection;
        this.ergonomic = ergonomic;
    }
}
