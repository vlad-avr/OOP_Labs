package com.example.lab3.inventory;

import com.example.lab3.actions.BuyAction;

public class ShopItem {
    private Item item;
    private BuyAction buyAction;
    private int price;
    private Shop shop;
    public ShopItem(Item item, Shop shop){
        this.item = item;
        calculatePrice();
        this.shop = shop;
        this.buyAction = new BuyAction(this);
    }

    private void calculatePrice(){
        if(item instanceof Weapon){
            price = (((Weapon) item).damage-1) * 5 + ((Weapon) item).piercing * 6;
        }else if(item instanceof Armor){
            price = ((Armor) item).protection * 8;
        }else if(item instanceof Consumable){
            price = ((Consumable) item).getHP()*5;
        }else{
            price = 0;
        }
    }

    public int getPrice(){
        return this.price;
    }

    public Item getItem(){
        return this.item;
    }

    public void getBought() {
        shop.removeItem(this);
    }

    public BuyAction getAction(){
        return this.buyAction;
    }
}
