package com.aircompany.db.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Brigade extends Entity{
    private String name;
    private boolean isStatic;

//    public Brigade(UUID id, String name, boolean isStatic){
//        super(id);
//        this.name = name;
//        this.isStatic = isStatic;
//    }
//
    public Brigade(UUID id) {
        super(id);
    }
//
//    public String getName(){
//        return name;
//    }
//    public boolean isStatic(){
//        return isStatic;
//    }
//    public void setName(String name){
//        this.name = name;
//    }
//    public void setStatic(boolean isStatic){
//        this.isStatic = isStatic;
//    }
}
