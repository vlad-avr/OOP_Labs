package com.aircompany.db.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;
@EqualsAndHashCode
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Entity {
    protected String id;

    public Entity(UUID id){
        this.id = id.toString();
    }
//    public Entity(){
//        this.id = UUID.randomUUID().toString();
//    }
//    public String getId(){
//        return id;
//    }
}
