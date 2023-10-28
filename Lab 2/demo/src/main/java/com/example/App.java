package com.example;

import com.example.flowers.Greenhouse;
import com.example.parser.XMLParser;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * 
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        XMLParser parser = new XMLParser("D:\\Java\\OOP_Labs\\Lab 2 fuck java\\demo\\Greenhouse.xml");
        Greenhouse greenhouse = parser.parseDOM();
        greenhouse.print();
    }
}
