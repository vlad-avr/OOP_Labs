package com.example.lab3.actions;

import com.example.lab3.entities.Player;

public class WaitAction extends Action{

    @Override
    public void performAction(Player player) {
        super.performAction(player);
        player.passTurn();
    }
}
