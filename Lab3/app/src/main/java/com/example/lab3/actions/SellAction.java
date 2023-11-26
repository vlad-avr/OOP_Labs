package com.example.lab3.actions;

import android.util.Log;

import com.example.lab3.entities.Player;
import com.example.lab3.inventory.Armor;
import com.example.lab3.inventory.Consumable;
import com.example.lab3.inventory.Item;
import com.example.lab3.inventory.Weapon;
import com.example.lab3.logic.ActionsLog;

public class SellAction extends Action{

    private Item item;
    private int price;

    public SellAction(Item item){
        this.item = item;
        calculatePrice();
    }

    private void calculatePrice(){
        if(item instanceof Weapon){
            price = (((Weapon) item).getDamageRaw()-1) * 5 + ((Weapon) item).piercing * 6;
        }else if(item instanceof Armor){
            price = ((Armor) item).protection * 8;
        }else if(item instanceof Consumable){
            price = ((Consumable) item).getHP()*5;
        }else{
            price = 0;
        }
        price = price/2;
    }

    @Override
    public void performAction(Player player){
        player.getInventory().remove(item);
        player.getLogger().stackLog("You sell " + item.name + item.getDesc(), ActionsLog.ATTENTION);
        player.addGold(price);
    }

}
