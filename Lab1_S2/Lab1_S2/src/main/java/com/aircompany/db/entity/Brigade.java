package com.aircompany.db.entity;

import java.util.UUID;

public class Brigade extends Entity{
    private String name;
    private boolean isStatic;

    public Brigade(UUID id, String name, boolean isStatic){
        super(id);
        this.name = name;
        this.isStatic = isStatic;
    }
    public Brigade(){super();}
    public Brigade(String name, boolean isStatic){
        super();
        this.name = name;
        this.isStatic = isStatic;
    }
    public String getName(){
        return name;
    }
    public boolean isStatic(){
        return isStatic;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setStatic(boolean isStatic){
        this.isStatic = isStatic;
    }
}
