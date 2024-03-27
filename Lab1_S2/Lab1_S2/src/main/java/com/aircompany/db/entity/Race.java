package com.aircompany.db.entity;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.UUID;

public class Race extends Entity{
    private String departurePlace;
    private String arrivalPlace;
    private Timestamp departureTime;
    private Timestamp arrivalTime;
    private int passengers;
    private double luggageWeight;

    public Race(UUID id) {
        super(id);
    }
    public Race(UUID id, String departurePlace, String arrivalPlace, Timestamp departureTime, Timestamp arrivalTime, int passengers, double luggageWeight) {
        super(id);
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.departurePlace = departurePlace;
        this.arrivalPlace = arrivalPlace;
        this.passengers = passengers;
        this.luggageWeight = luggageWeight;
    }

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
