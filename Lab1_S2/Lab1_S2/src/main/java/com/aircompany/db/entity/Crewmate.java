package com.aircompany.db.entity;

import java.util.UUID;

public class Crewmate extends Entity{
    public Crewmate(UUID id) {
        super(id);
    }

    public static enum Qualification{
        STUART,
        PILOT,
        RADIO_OFFICER
    }
    private String name;
    private Qualification qualification;
    private UUID brigadeId;

    public Crewmate(UUID id, String name, Qualification qualification, UUID brigadeId){
        super(id);
        this.name = name;
        this.qualification = qualification;
        this.brigadeId = brigadeId;
    }
    public Crewmate(){super();}
    public Crewmate(String name, Qualification qualification, UUID brigadeId){
        super();
        this.name = name;
        this.qualification = qualification;
        this.brigadeId = brigadeId;
    }
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
