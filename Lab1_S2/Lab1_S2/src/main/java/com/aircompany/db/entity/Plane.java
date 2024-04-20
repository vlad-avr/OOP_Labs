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
public class Plane extends Entity{
    private String model;
    private int passengerSeats;
    private double maxLuggage;
    private int maxFlightInMins;

//    public Plane(UUID id, String model, double maxLuggage, int maxFlightInMins, int passengerSeats){
//        super(id);
//        this.model = model;
//        this.maxLuggage = maxLuggage;
//        this.maxFlightInMins = maxFlightInMins;
//        this.passengerSeats = passengerSeats;
//    }
//    public Plane(String model, double maxLuggage, int maxFlightInMins, int passengerSeats){
//        super();
//        this.model = model;
//        this.maxLuggage = maxLuggage;
//        this.maxFlightInMins = maxFlightInMins;
//        this.passengerSeats = passengerSeats;
//    }

    public Plane(UUID id) {
        super(id);
    }

//    public String getModel(){
//        return model;
//    }
//    public int getPassengerSeats(){
//        return passengerSeats;
//    }
//    public double getMaxLuggage(){
//        return maxLuggage;
//    }
//    public int getMaxFlightInMins(){
//        return maxFlightInMins;
//    }
//
//    public void setModel(String model){
//        this.model = model;
//    }
//    public void setPassengerSeats(int passengerSeats){
//        this.passengerSeats = passengerSeats;
//    }
//    public void setMaxLuggage(double maxLuggage){
//        this.maxLuggage = maxLuggage;
//    }
//    public void setMaxFlightInMins(int maxFlightInMins){
//        this.maxFlightInMins = maxFlightInMins;
//    }
}
