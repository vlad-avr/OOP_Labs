package com.aircompany.db.entity;

import java.sql.Time;
import java.sql.Timestamp;

public class Race extends Entity{
    private String departurePlace;
    private String arrivalPlace;
    private Timestamp departureTime;
    private Timestamp arrivalTime;
    private int passengers;
    private double luggageWeight;

    public String getDeparturePlace(){
        return departurePlace;
    }
    public String getArrivalPlace(){
        return arrivalPlace;
    }
    public Timestamp getDepartureTime(){
        return departureTime;
    }
    public Timestamp getArrivalTime(){
        return arrivalTime;
    }
    public int getPassengers(){
        return passengers;
    }
    public double getLuggageWeight(){
        return luggageWeight;
    }

    public void setDeparturePlace(String departurePlace){
        this.departurePlace = departurePlace;
    }
    public void setArrivalPlace(String arrivalPlace){
        this.arrivalPlace = arrivalPlace;
    }
    public void setPassengers(int passengers){
        this.passengers = passengers;
    }
    public void setLuggageWeight(double luggageWeight){
        this.luggageWeight = luggageWeight;
    }
    public void setDepartureTime(Timestamp departureTime){
        this.departureTime = departureTime;
    }
    public void setArrivalTime(Timestamp arrivalTime){
        this.arrivalTime = arrivalTime;
    }
}
