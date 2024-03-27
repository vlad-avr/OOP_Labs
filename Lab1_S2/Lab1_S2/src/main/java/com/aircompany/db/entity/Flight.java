package com.aircompany.db.entity;

import java.util.UUID;

public class Flight extends Entity{
    private String raceId;
    private String brigadeId;
    private String planeId;

    public Flight(UUID id) {
        super(id);
    }

    public String getRaceId(){
        return raceId;
    }
    public String getBrigadeId(){
        return brigadeId;
    }
    public String getPlaneId(){
        return planeId;
    }

    public void setBrigadeId(String brigadeId){
        this.brigadeId = brigadeId;
    }
    public void setRaceId(String raceId){
        this.raceId = raceId;
    }
    public void setPlaneId(String planeId){
        this.planeId = planeId;
    }
}
