package com.aircompany.db.entity;

public class Crewmate extends Entity{
    public static enum Qualification{
        STUART,
        PILOT,
        RADIO_OFFICER
    }
    private String name;
    private Qualification qualification;
    private int brigade_id;
}
