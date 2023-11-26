package com.example.lab3.actions;

import com.example.lab3.inventory.ShopItem;

public class BuyAction extends Action{
    private ShopItem holder;

    public BuyAction(ShopItem holder){
        this.holder = holder;
        setPrompt("Buy " + holder.getItem().name + " " + holder.getItem().getAction().getExtraPrompt() + " for " + holder.getPrice() + " gold");
    }
}
