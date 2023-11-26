package com.example.lab3.inventory;

import com.example.lab3.logic.Game;

import java.util.ArrayList;
import java.util.List;

public class Shop {
    private List<ShopItem> items = new ArrayList<>();
    private Game game;

    private final int maxTimeToRestock = 15;
    private int timeToRestock = maxTimeToRestock;

    public Shop(Game game){
        this.game = game;
        restock();
    }

    public List<ShopItem> getItems(){
        return this.items;
    }

    public void update(){
        if(timeToRestock == 0){
            restock();
            timeToRestock = maxTimeToRestock;
        }else{
            timeToRestock--;
        }
    }

    private void restock(){
        items.clear();
        items.add(new ShopItem(new Weapon(1, 0, false, false, "Sword"), this));
        items.add(new ShopItem(new Armor(0, 1.0, "Armor"), this));
        for(int i = 1; i <= 3; i++){
            int roll = Game.rnd.nextInt(3);
            switch (roll){
                case 0:
                    items.add(new ShopItem(ItemFactory.makeWeapon(i, ItemFactory.SWORD), this));
                    break;
                case 1:
                    items.add(new ShopItem(ItemFactory.makeWeapon(i, ItemFactory.HAMMER), this));
                    break;
                case 2:
                    items.add(new ShopItem(ItemFactory.makeWeapon(i, ItemFactory.AXE), this));
                    break;
            }
        }
        for(int i = 1; i <= 3; i++){
            items.add(new ShopItem(ItemFactory.makeArmor(i, 0.8), this));
        }
        for (int i = 1; i <= 4; i++){
            items.add(new ShopItem(ItemFactory.makeConsumable(i*5 - Game.rnd.nextInt(5)), this));
        }
    }

    public void removeItem(ShopItem shopItem) {
        items.remove(shopItem);
    }
}
