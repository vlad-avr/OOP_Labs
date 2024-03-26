package com.aircompany.db.entity;

public class Plane extends Entity{
    private String model;
    private int minPilots;
    private int minStuarts;
    private int minRadioOfficers;
    private int passengerSeats;
    private double maxLuggage;
    private int maxFlightInMins;

    public String getModel(){
        return model;
    }
    public int getMinPilots(){
        return minPilots;
    }
    public int getMinStuarts(){
        return minStuarts;
    }
    public int getMinRadioOfficers(){
        return minRadioOfficers;
    }
    public int getPassengerSeats(){
        return passengerSeats;
    }
    public double getMaxLuggage(){
        return maxLuggage;
    }
    public int getMaxFlightInMins(){
        return maxFlightInMins;
    }

    public void setModel(String model){
        this.model = model;
    }
    public void setMinPilots(int minPilots){
        this.minPilots = minPilots;
    }
    public void setMinStuarts(int minStuarts){
        this.minStuarts = minStuarts;
    }
    public void setMinRadioOfficers(int minRadioOfficers){
        this.minRadioOfficers = minRadioOfficers;
    }
    public void setPassengerSeats(int passengerSeats){
        this.passengerSeats = passengerSeats;
    }
    public void setMaxLuggage(double maxLuggage){
        this.maxLuggage = maxLuggage;
    }
    public void setMaxFlightInMins(int maxFlightInMins){
        this.maxFlightInMins = maxFlightInMins;
    }
}
