package com.aircompany.db.entity;

import java.util.UUID;

public class Entity {
    protected String id;

    public Entity(UUID id){
        this.id = id.toString();
    }
    public Entity(){
        this.id = UUID.randomUUID().toString();
    }
    public String getId(){
        return id;
    }
}
