package com.example.lab3.actions;

import com.example.lab3.entities.GameObject;
import com.example.lab3.entities.Player;

public abstract class Action {
    private String prompt = "none";
    public abstract void performAction(Player player);

    public void setPrompt(String prompt){
        this.prompt = prompt;
    }
    public String getPrompt() {
        return prompt;
    }
}
