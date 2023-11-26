package com.example.lab3.inventory;

import com.example.lab3.actions.EquipAction;

public class Armor extends Item{
    public final int protection;
    public final double ergonomic;

    public Armor(int protection, double ergonomic, String name){
        super(name);
        action = new EquipAction(this);
        this.protection = protection;
        this.ergonomic = ergonomic;
        action.setPrompt(name);
        action.setExtraPrompt(getDesc());
    }

    public String getDesc(){
        return "(PROT " + protection + "|ERG " + ergonomic +")";
    }
}
