package com.example.lab3.actions;

import com.example.lab3.entities.Player;
import com.example.lab3.inventory.Armor;
import com.example.lab3.inventory.Consumable;
import com.example.lab3.inventory.Item;
import com.example.lab3.inventory.Weapon;

public class ConsumeAction extends InventoryAction{

    private Consumable holder;

    public ConsumeAction(Consumable holder){
        this.holder = holder;
    }

    @Override
    public void performAction(Player player){
        player.heal(holder.getHP());
    }
}
