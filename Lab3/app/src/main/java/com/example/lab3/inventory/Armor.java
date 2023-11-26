package com.example.lab3.inventory;

import com.example.lab3.actions.EquipAction;

public class Armor extends Item{
    public final int protection;
    public final int maxDurability;
    private int durability;

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
        return "(PROT " + protection + "|DUR " + durability + "\\" + maxDurability +")";
    }

    public void reduceDurability() {
        if(durability > 0){
            durability--;
        }
        action.setExtraPrompt(getDesc());
    }

    public int getProtection(){
        if(durability == 0){
            return 0;
        }
        return protection;
    }
}
