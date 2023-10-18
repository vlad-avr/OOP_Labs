package com.example.flowers;

public class Flower {
    public static enum Soil {
        podzol,
        turf_podzol,
        regular,
    }

    public static enum Reproduction {
        leaves,
        stalks,
        seeds,
    }

    public String id;
    public String name;
    public Soil soil;
    public String origin;
    public Visuals visuals = new Visuals();
    public Tips tips = new Tips();
    public Reproduction reproduction;

    public class Visuals {
        public double stalk_length;
        public String color;
        public double flower_diameter;

        public String toString() {
            return "\n\tAverage stalk length (cm) : " + stalk_length + "\n\tColor : " + color
                    + "\n\tAverage flower diameter (cm) : " + flower_diameter;
        }
    }

    public class Tips {
        public double temp;
        public int light;
        public int water;

        public String toString() {
            return "\n\tIdeal average temperature : " + temp + "\n\tHours of illumination needed per day : " + light
                    + "\n\tDaily needs in water (ml) : " + water;
        }
    }

    public String toString() {
        return "\nID : " + id + "\nName : " + name + "\nOrigin : " + origin + "\nSoil type : " + soil.toString()
                + "\nVisual qualities : " + visuals.toString() + "\nGardening tips : " + tips.toString()
                + "\nReproduces with " + reproduction.toString();
    }

}
