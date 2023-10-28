package com.example;

import com.example.flowers.Greenhouse;
import com.example.parser.XMLParser;

public final class App {
    private App() {
    }

    public static void main(String[] args) {
        XMLParser parser = new XMLParser("D:\\Java\\OOP_Labs\\Lab 2\\demo\\Greenhouse.xml");
        Greenhouse DOMhouse = parser.parseDOM();
        System.out.println("\n DOM PARSER : \n");
        DOMhouse.print();
        Greenhouse SAXhouse = parser.parseSAX();
        System.out.println("\n SAX PARSER : \n");
        SAXhouse.print();
        Greenhouse STAXhouse = parser.parseSTAX();
        System.out.println("\n STAX PARSER : \n");
        STAXhouse.print();
    }
}
