package com.aircompany.db.entity;

import java.util.UUID;

public class Crewmate extends Entity{
    public static enum Qualification{
        STUART,
        PILOT,
        RADIO_OFFICER
    }
    private String name;
    private Qualification qualification;
    private UUID brigadeId;
    public String getName(){
        return name;
    }
    public Qualification getQualification(){
        return qualification;
    }
    public UUID getBrigadeId(){
        return brigadeId;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setQualification(Qualification qualification){
        this.qualification = qualification;
    }
    public void setBrigadeId(UUID brigadeId){
        this.brigadeId = brigadeId;
    }
}
