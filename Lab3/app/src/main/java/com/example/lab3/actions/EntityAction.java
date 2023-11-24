package com.example.lab3.actions;

import com.example.lab3.entities.Entity;
import com.example.lab3.entities.Player;

public class EntityAction extends Action{

    private Entity holder;
    public EntityAction(Entity holder) {
        this.holder = holder;
    }

    @Override
    public void performAction(Player player) {
        holder.stackDamage(player.dealDamage());
    }
}