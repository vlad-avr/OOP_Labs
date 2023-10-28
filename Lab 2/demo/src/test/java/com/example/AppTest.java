package com.example;

import org.junit.jupiter.api.Test;

import com.example.flowers.Flower;
import com.example.flowers.Greenhouse;
import com.example.parser.XMLParser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

class AppTest {
    @Test
    void testApp() {
        final double Eps = 0.00001;
        XMLParser parser = new XMLParser("D:\\Java\\OOP_Labs\\Lab 2\\demo\\Test.xml");
        List<Greenhouse> greenhouses = new ArrayList<>();
        greenhouses.add(parser.parseDOM());
        greenhouses.add(parser.parseSAX());
        greenhouses.add(parser.parseSTAX());
        for (Greenhouse greenhouse : greenhouses) {
            assertEquals(greenhouse.length(), 2);
            Flower flower = greenhouse.get(0);
            assertEquals(flower.id, "ID-1");
            assertEquals(flower.name, "Tulip");
            assertEquals(flower.soil, Flower.Soil.podzol);
            assertEquals(flower.origin, "Netherlands");
            assertEquals(flower.visuals.stalk_length, 30.0, Eps);
            assertEquals(flower.visuals.color, "red");
            assertEquals(flower.visuals.flower_diameter, 10.0, Eps);
            assertEquals(flower.tips.temp, 18.0, Eps);
            assertEquals(flower.tips.light, 7);
            assertEquals(flower.tips.water, 10, Eps);
            assertEquals(flower.reproduction, Flower.Reproduction.leaves);
            flower = greenhouse.get(1);
            assertEquals(flower.id, "ID-2");
            assertEquals(flower.name, "Rose");
            assertEquals(flower.soil, Flower.Soil.turf_podzol);
            assertEquals(flower.origin, "England");
            assertEquals(flower.visuals.stalk_length, 150.0, Eps);
            assertEquals(flower.visuals.color, "deep-red");
            assertEquals(flower.visuals.flower_diameter, 15.0, Eps);
            assertEquals(flower.tips.temp, 20.0, Eps);
            assertEquals(flower.tips.light, 7);
            assertEquals(flower.tips.water, 100, Eps);
            assertEquals(flower.reproduction, Flower.Reproduction.stalks);
        }
    }
}
