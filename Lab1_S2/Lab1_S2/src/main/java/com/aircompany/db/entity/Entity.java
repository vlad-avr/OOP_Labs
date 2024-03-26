package com.aircompany.db.entity;

import java.util.UUID;

public class Entity {
    protected UUID id;

    public Entity(UUID id){
        this.id = id;
    }
    public Entity(){
        this.id = UUID.randomUUID();
    }
    public UUID getId(){
        return id;
    }
}
