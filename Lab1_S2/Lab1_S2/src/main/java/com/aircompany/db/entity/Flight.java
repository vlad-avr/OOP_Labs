package com.aircompany.db.entity;

import java.util.UUID;

public class Flight extends Entity{
    private UUID raceId;
    private UUID brigadeId;
    private UUID planeId;

    public Flight(UUID id) {
        super(id);
    }

    public UUID getRaceId(){
        return raceId;
    }
    public UUID getBrigadeId(){
        return brigadeId;
    }
    public UUID getPlaneId(){
        return planeId;
    }

    public void setBrigadeId(UUID brigadeId){
        this.brigadeId = brigadeId;
    }
    public void setRaceId(UUID raceId){
        this.raceId = raceId;
    }
    public void setPlaneId(UUID planeId){
        this.planeId = planeId;
    }
}
