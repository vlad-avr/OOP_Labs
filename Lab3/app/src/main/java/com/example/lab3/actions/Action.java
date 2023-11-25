package com.example.lab3.actions;

import com.example.lab3.entities.Player;

public abstract class Action {
    private String prompt = "none";
    private String extraPrompt = "";
    public void performAction(Player player){
        extraPrompt = "";
    }

    public void setPrompt(String prompt){
        this.prompt = prompt;
    }
    public String getPrompt() {
        return prompt;
    }
    public String getExtraPrompt(){
        return extraPrompt;
    }
    public void setExtraPrompt(String posPrompt){
        this.extraPrompt = posPrompt;
    }
}
