package com.example.lab3.inventory;

import com.example.lab3.actions.ConsumeAction;
import com.example.lab3.actions.SellAction;

public class Consumable extends Item{

    private int healingPoints;

    public Consumable(String name, int HP) {
        super(name);
        this.healingPoints = HP;
        action = new ConsumeAction(this);
        action.setPrompt(name);
        action.setExtraPrompt("+" + HP);
        sellAction = new SellAction(this);
    }

    public int getHP(){
        return this.healingPoints;
    }

    @Override
    public String getDesc(){return " +" + healingPoints;}
}
