package com.example.lab3.inventory;

import com.example.lab3.graphics.Sprite;

public class Item {
    private Sprite image;
    private String desc;

    public Item(Sprite image, String desc){
        this.image = image;
        this.desc = desc;
    }
}
