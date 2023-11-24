package com.example.lab3.actions;

import com.example.lab3.entities.GameObject;
import com.example.lab3.entities.Player;

public abstract class Action {

    protected GameObject holder;

    public Action(GameObject holder){
        this.holder = holder;
    }

    public abstract void performAction(Player player);

}
