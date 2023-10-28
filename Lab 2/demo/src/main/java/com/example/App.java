package com.example;

import com.example.flowers.Greenhouse;
import com.example.parser.XMLParser;

public final class App {
    private App() {
    }

    public static void main(String[] args) {
        XMLParser parser = new XMLParser("D:\\Java\\OOP_Labs\\Lab 2\\demo\\Greenhouse.xml");
        Greenhouse DOMhouse = parser.parseDOM();
        DOMhouse.print();
        Greenhouse SAXhouse = parser.parseSAX();
        SAXhouse.print();
    }
}
