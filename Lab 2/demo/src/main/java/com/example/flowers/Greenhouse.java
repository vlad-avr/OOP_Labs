package com.example.flowers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Greenhouse {
    private List<Flower> flowers = new ArrayList<>();

    public Flower get(int i) {
        try {
            return flowers.get(i);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Flower last() {
        return flowers.get(flowers.size() - 1);
    }

    public void set(int i, Flower flower) {
        try {
            flowers.set(i, flower);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    public void add(Flower flower) {
        flowers.add(flower);
    }

    public void print() {
        System.out.println("\nFlowers in greenhouse : " + flowers.size());
        for (Flower flower : flowers) {
            System.out.println(flower.toString());
        }
    }

    //Sorts list by flower names in alphabetical order
    public void sort() {
        Collections.sort(flowers, new Comparator<Flower>() {
            @Override
            public int compare(Flower lhs, Flower rhs) {
                // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
                if (lhs.name.length() > rhs.name.length()) {
                    return 1;
                } else if (lhs.name.length() < rhs.name.length()) {
                    return -1;
                } else {
                    for (int i = 0; i < lhs.name.length(); i++) {
                        if (lhs.name.charAt(i) > rhs.name.charAt(i)) {
                            return 1;
                        } else if (lhs.name.charAt(i) < rhs.name.charAt(i)) {
                            return -1;
                        } else {
                            continue;
                        }
                    }
                }
                return 0;
            }
        });
    }

    // For testing
    public int length() {
        return flowers.size();
    }
}
