package com.example.lab3.inventory;

import com.example.lab3.actions.InventoryAction;
import com.example.lab3.actions.SellAction;
import com.example.lab3.graphics.Sprite;

public abstract class Item {

    public final String name;
    protected InventoryAction action;
    protected SellAction sellAction;

    public Item(String name){
        this.name = name;
    }
    public InventoryAction getAction(){
        return action;
    }
    public SellAction getSellAction(){return sellAction;}

    public abstract String getDesc();

}
