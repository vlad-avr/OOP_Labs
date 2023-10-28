package com.example.parser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.namespace.QName;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.example.flowers.Flower;
import com.example.flowers.Greenhouse;

public class MySAXParser {

    public Greenhouse parseSAX(String filePath) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            Greenhouse greenhouse = new Greenhouse();
            SAXParser parser = factory.newSAXParser();
            MyHandler handler = new MyHandler(greenhouse);
            parser.parse(filePath, handler);
            return handler.getGreenhouse();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Greenhouse parseSTAX(String xmlPath) {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try {
            XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream(xmlPath));
            Greenhouse greenhouse = new Greenhouse();
            while (reader.hasNext()) {
                XMLEvent nextEvent = reader.nextEvent();
                if (nextEvent.isStartElement()) {
                    StartElement startElement = nextEvent.asStartElement();
                    switch (startElement.getName().getLocalPart()) {
                        case "flower":
                            greenhouse.add(new Flower());
                            Attribute id = startElement.getAttributeByName(new QName("id"));
                            greenhouse.last().id = id.getValue();
                            break;
                        case "name":
                            nextEvent = reader.nextEvent();
                            greenhouse.last().name = nextEvent.asCharacters().getData();
                            break;
                        case "origin":
                            nextEvent = reader.nextEvent();
                            greenhouse.last().origin = nextEvent.asCharacters().getData();
                            break;
                        case "soil":
                            nextEvent = reader.nextEvent();
                            greenhouse.last().soil = Flower.Soil.valueOf(nextEvent.asCharacters().getData());
                            break;
                        case "reproduction":
                            nextEvent = reader.nextEvent();
                            greenhouse.last().reproduction = Flower.Reproduction.valueOf(nextEvent.asCharacters().getData());
                            break;
                        case "stalk_length":
                            nextEvent = reader.nextEvent();
                            greenhouse.last().visuals.stalk_length = Double.parseDouble(nextEvent.asCharacters().getData());
                            break;
                        case "color":
                            nextEvent = reader.nextEvent();
                            greenhouse.last().visuals.color = nextEvent.asCharacters().getData();
                            break;
                        case "flower_diameter":
                            nextEvent = reader.nextEvent();
                            greenhouse.last().visuals.flower_diameter = Double.parseDouble(nextEvent.asCharacters().getData());
                            break;
                        case "temperature":
                            nextEvent = reader.nextEvent();
                            greenhouse.last().tips.temp = Double.parseDouble(nextEvent.asCharacters().getData());
                            break;
                        case "illumination":
                            nextEvent = reader.nextEvent();
                            greenhouse.last().tips.light = Integer.parseInt(nextEvent.asCharacters().getData());
                            break;
                        case "irrigation":
                            nextEvent = reader.nextEvent();
                            greenhouse.last().tips.water = Integer.parseInt(nextEvent.asCharacters().getData());
                            break;
                        default:
                            break;
                    }
                }
            }
            return greenhouse;
        } catch (FileNotFoundException | XMLStreamException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    class MyHandler extends DefaultHandler {
        private Greenhouse greenhouse;
        private StringBuilder curValue;

        public MyHandler(Greenhouse greenhouse) {
            super();
            this.greenhouse = greenhouse;
        }

        public Greenhouse getGreenhouse() {
            return greenhouse;
        }

        @Override
        public void startDocument() throws SAXException {
            greenhouse = new Greenhouse();
        }

        @Override
        public void startElement(String uri, String lName, String qName, Attributes attr) throws SAXException {
            if (qName.equals("flower")) {
                greenhouse.add(new Flower());
                greenhouse.last().id = attr.getValue("id");
            } else if (qName.equals("name") || qName.equals("soil") || qName.equals("origin")
                    || qName.equals("reproduction") || qName.equals("stalk_length") || qName.equals("color")
                    || qName.equals("flower_diameter") || qName.equals("temperature") || qName.equals("illumination")
                    || qName.equals("irrigation")) {
                curValue = new StringBuilder();
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            switch (qName) {
                case "name":
                    greenhouse.last().name = curValue.toString();
                    break;
                case "soil":
                    greenhouse.last().soil = Flower.Soil.valueOf(curValue.toString());
                    break;
                case "origin":
                    greenhouse.last().origin = curValue.toString();
                    break;
                case "reproduction":
                    greenhouse.last().reproduction = Flower.Reproduction.valueOf(curValue.toString());
                    break;
                case "stalk_length":
                    greenhouse.last().visuals.stalk_length = Double.parseDouble(curValue.toString());
                    break;
                case "color":
                    greenhouse.last().visuals.color = curValue.toString();
                    break;
                case "flower_diameter":
                    greenhouse.last().visuals.flower_diameter = Double.parseDouble(curValue.toString());
                    break;
                case "temperature":
                    greenhouse.last().tips.temp = Double.parseDouble(curValue.toString());
                    break;
                case "illumination":
                    greenhouse.last().tips.light = Integer.parseInt(curValue.toString());
                    break;
                case "irrigation":
                    greenhouse.last().tips.water = Integer.parseInt(curValue.toString());
                    break;
                default:
                    break;
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            if (curValue == null) {
                curValue = new StringBuilder();
            } else {
                curValue.append(ch, start, length);
            }
        }
    }
}
