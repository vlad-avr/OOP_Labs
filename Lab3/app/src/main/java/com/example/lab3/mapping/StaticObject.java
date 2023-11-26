package com.example.lab3.mapping;

import com.example.lab3.actions.Action;
import com.example.lab3.actions.UtilityAction;
import com.example.lab3.graphics.Sprite;

public class StaticObject {
    private Sprite sprite;
    private UtilityAction action;
    private Tile tile;
    public StaticObject(Sprite sprite, String prompt){
        this.sprite = sprite;
        this.action = new UtilityAction(this);
        action.setPrompt(prompt);
    }

    public Sprite getSprite(){
        return this.sprite;
    }

    public UtilityAction getAction(){
        return this.action;
    }

    public void setTile(Tile tile){
        this.tile = tile;
    }

    public void destroySelf(){
        tile.removeObj();
    }
}
