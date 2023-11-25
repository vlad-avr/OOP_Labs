package com.example.lab3.inventory;

import com.example.lab3.actions.InventoryAction;
import com.example.lab3.graphics.Sprite;

public abstract class Item {
    private Sprite image;
    private String desc;

    private InventoryAction action;
    public Item(Sprite image, String desc){
        this.image = image;
        this.desc = desc;
    }

}
