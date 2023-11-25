package com.example.lab3.inventory;

import com.example.lab3.actions.EquipAction;

public class Consumable extends Item{

    public static final int sortingWeight = 1;

    public Consumable(String name) {
        super(name);
        action = new EquipAction(this);
    }
}
