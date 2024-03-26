package com.aircompany.db.entity;

public class Brigade extends Entity{
    private String name;
    private boolean isStatic;
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
