package com.example.lab3.inventory;

import com.example.lab3.logic.Game;

import java.util.ArrayList;
import java.util.List;

public class Shop {
    private List<ShopItem> items = new ArrayList<>();
    private Game game;

    public Shop(Game game){
        this.game = game;
    }

    public List<ShopItem> getItems(){
        return this.items;
    }

    public void genItem(){

    }

}
