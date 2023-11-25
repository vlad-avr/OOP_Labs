package com.example.lab3.actions;

import com.example.lab3.entities.Player;
import com.example.lab3.inventory.Armor;
import com.example.lab3.inventory.Item;
import com.example.lab3.inventory.Weapon;

public class EquipAction extends InventoryAction{
    private Item holder;

    public EquipAction(Item holder){
        this.holder = holder;
    }

    @Override
    public void performAction(Player player){
        if(holder instanceof Weapon){
            player.setWeapon((Weapon) holder);
        }else if(holder instanceof Armor){
            player.setArmor((Armor) holder);
        }
    }
}
