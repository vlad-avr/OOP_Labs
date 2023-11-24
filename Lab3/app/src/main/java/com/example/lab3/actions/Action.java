package com.example.lab3.actions;

import com.example.lab3.entities.GameObject;
import com.example.lab3.entities.Player;

public abstract class Action {
    private String prompt = "none";
    private String positionalPrompt = "";
    public void performAction(Player player){
        positionalPrompt = "";
    }

    public void setPrompt(String prompt){
        this.prompt = prompt;
    }
    public String getPrompt() {
        return prompt;
    }
    public String getPositionalPrompt(){
        return positionalPrompt;
    }
    public void setPositionalPrompt(String posPrompt){
        this.positionalPrompt = posPrompt;
    }
}
