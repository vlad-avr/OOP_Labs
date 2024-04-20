package com.aircompany.db.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Flight extends Entity{
    private String raceId;
    private String brigadeId;
    private String planeId;

    public Flight(UUID id) {
        super(id);
    }
//
//    public String getRaceId(){
//        return raceId;
//    }
//    public String getBrigadeId(){
//        return brigadeId;
//    }
//    public String getPlaneId(){
//        return planeId;
//    }
//
//    public void setBrigadeId(String brigadeId){
//        this.brigadeId = brigadeId;
//    }
//    public void setRaceId(String raceId){
//        this.raceId = raceId;
//    }
//    public void setPlaneId(String planeId){
//        this.planeId = planeId;
//    }
}
