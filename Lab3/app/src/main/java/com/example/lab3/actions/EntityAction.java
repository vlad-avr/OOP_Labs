package com.example.lab3.actions;

import com.example.lab3.entities.Enemy;
import com.example.lab3.entities.Entity;
import com.example.lab3.entities.Player;

public class EntityAction extends Action{

    private Enemy holder;
    public EntityAction(Enemy holder) {
        this.holder = holder;
    }

    @Override
    public void performAction(Player player) {
        super.performAction(player);
        holder.stackDamage(holder.calculateDamage(player.getDamage()));
    }
}
