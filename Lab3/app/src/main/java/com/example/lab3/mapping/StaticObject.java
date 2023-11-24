package com.example.lab3.mapping;

import com.example.lab3.actions.Action;
import com.example.lab3.actions.UtilityAction;
import com.example.lab3.graphics.Sprite;

public class StaticObject {
    private Sprite sprite;
    private UtilityAction action;
    public StaticObject(Sprite sprite, String prompt){
        this.sprite = sprite;
        this.action = new UtilityAction();
        action.setPrompt(prompt);
    }

    public Sprite getSprite(){
        return this.sprite;
    }

    public UtilityAction getAction(){
        return this.action;
    }
}
