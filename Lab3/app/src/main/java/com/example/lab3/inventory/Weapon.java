package com.example.lab3.inventory;

import com.example.lab3.actions.EquipAction;
import com.example.lab3.logic.Game;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Weapon extends Item{

    private final int damage;
    public final int piercing;
    public final boolean canChop;
    public final boolean canMine;

    private double critChance = 0.0;

    public int durability;
    public final int maxDurability;


    public Weapon(int damage, int piercing, int maxDurability, boolean canChop, boolean canMine, String name){
        super(name);
        action = new EquipAction(this);
        this.maxDurability = maxDurability;
        durability = maxDurability;
        this.canChop = canChop;
        this.canMine = canMine;
        this.damage = damage;
        this.piercing = piercing;
        action.setPrompt(name);
        action.setExtraPrompt(getDesc());
    }

    public String getDesc(){
        DecimalFormat dc = new DecimalFormat("#.#");
        dc.setRoundingMode(RoundingMode.FLOOR);
        return "(DMG " + damage + "|PRC " + piercing + "|DUR " + durability + "\\" + maxDurability + " |CRT " + dc.format(critChance) + ")";
    }

    public int getDamage() {
        if(durability == 0){
            return 0;
        }
        double roll = Game.rnd.nextDouble();
        if(roll < critChance){
            return damage*2;
        }else{
            return damage;
        }
    }

    public int getDamageRaw(){
        return damage;
    }

    public void reduceDurability(){
        if(durability > 0){
            durability--;
        }
        action.setExtraPrompt(getDesc());
    }


    public int getDurability() {
        return maxDurability - durability;
    }

    public void moreCrit(){
        critChance += 0.1;
        action.setExtraPrompt(getDesc());
    }

    public boolean maxCrit(){
        return critChance >= 1.0;
    }
    public void mend(){
        this.durability = maxDurability;
        action.setExtraPrompt(getDesc());
    }
    public boolean isFixed(){
        return durability == maxDurability;
    }
}
