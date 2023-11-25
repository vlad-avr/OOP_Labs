package com.example.lab3.inventory;

import com.example.lab3.actions.InventoryAction;
import com.example.lab3.graphics.Sprite;

public abstract class Item {
    private InventoryAction action;

    public InventoryAction getAction(){
        return action;
    }

}
