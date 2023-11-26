package com.example.lab3.inventory;

import com.example.lab3.actions.EquipAction;

public class Weapon extends Item{

    public final int damage;
    public final int piercing;
    public final boolean canChop;
    public final boolean canMine;

    public Weapon(int damage, int piercing, boolean canChop, boolean canMine, String name){
        super(name);
        action = new EquipAction(this);
        this.canChop = canChop;
        this.canMine = canMine;
        this.damage = damage;
        this.piercing = piercing;
        action.setPrompt(name);
        action.setExtraPrompt(getDesc());
    }

    public String getDesc(){
        return "(DMG " + damage + "|PRC " + piercing + "|CHP " + canChop + "|MN " + canMine + ")";
    }
}
