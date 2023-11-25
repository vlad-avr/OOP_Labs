package com.example.lab3.inventory;

import com.example.lab3.actions.ConsumeAction;
import com.example.lab3.actions.EquipAction;

public class Consumable extends Item{

    public static final int sortingWeight = 1;
    private int healingPoints;

    public Consumable(String name, int HP) {
        super(name);
        this.healingPoints = HP;
        action = new ConsumeAction(this);
    }

    public int getHP(){
        return this.healingPoints;
    }
}
