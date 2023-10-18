package com.example.flowers;

import java.util.ArrayList;
import java.util.List;

public class Greenhouse {
    private List<Flower> flowers = new ArrayList<>();

    public Flower get(int i){
        try {
            return flowers.get(i);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void set(int i, Flower flower){
        try {
            flowers.set(i, flower);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    public void add(Flower flower){
        flowers.add(flower);
    }
}
