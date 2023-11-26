package com.example.lab3.inventory;

import com.example.lab3.actions.EquipAction;
import com.example.lab3.logic.Game;

public class Armor extends Item{
    public final int protection;
    public final int maxDurability;
    private int durability;

    private double dodgeChance = 0.0;

    public Armor(int protection, int maxDurability, String name){
        super(name);
        action = new EquipAction(this);
        this.protection = protection;
        this.maxDurability = maxDurability;
        durability = maxDurability;
        action.setPrompt(name);
        action.setExtraPrompt(getDesc());
    }

    public String getDesc(){
        return "(PROT " + protection + "|DUR " + durability + "\\" + maxDurability + " |DG " + dodgeChance + ")";
    }

    public void reduceDurability() {
        if(durability > 0){
            durability--;
        }
        action.setExtraPrompt(getDesc());
    }

    public int getDurability(){
        return maxDurability - durability;
    }

    public void moreDodge(){
        dodgeChance += 0.1;
    }

    public boolean maxDodge(){
        return dodgeChance >= 1.0;
    }

    public void mend(){
        this.durability = maxDurability;
    }

    public boolean isFixed(){
        return durability == maxDurability;
    }

    public int getProtection(){
        if(durability == 0){
            return 0;
        }
        double roll = Game.rnd.nextDouble();
        if(roll < dodgeChance){
            return protection*2;
        }
        return protection;
    }
}
