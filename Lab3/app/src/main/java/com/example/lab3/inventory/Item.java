package com.example.lab3.inventory;

import com.example.lab3.actions.InventoryAction;
import com.example.lab3.graphics.Sprite;

public abstract class Item {

    public final int sortingWeight = 0;

    public final String name;
    protected InventoryAction action;

    public Item(String name){
        this.name = name;
    }
    public InventoryAction getAction(){
        return action;
    }

}
