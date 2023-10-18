package com.example.parser;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.example.flowers.Flower;
import com.example.flowers.Greenhouse;

public class XMLParser {
    private final String xmlPath;

    public XMLParser(String xmlPath) {
        this.xmlPath = xmlPath;
    }

    public Greenhouse parse() {
        Document doc = getDocument();
        if (doc != null) {
            Greenhouse greenhouse = new Greenhouse();
            NodeList nodeList = doc.getElementsByTagName("flower");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Flower flower = parseSingleFlower(nodeList.item(i));
                if (flower != null) {
                    greenhouse.add(flower);
                }
            }
            return greenhouse;
        } else {
            return null;
        }
    }

    public Flower parseSingleFlower(Node node) {
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Flower flower = new Flower();
            Element elem = (Element) node;
            flower.id = elem.getAttribute("id");
            flower.name = elem.getElementsByTagName("name").item(0).getTextContent();
            flower.soil = Flower.Soil.valueOf(elem.getElementsByTagName("soil").item(0).getTextContent());
            flower.origin = elem.getElementsByTagName("origin").item(0).getTextContent();
            Element visuals = (Element) elem.getElementsByTagName("visuals").item(0);
            flower.visuals.stalk_length = Double
                    .parseDouble(visuals.getElementsByTagName("stalk_length").item(0).getTextContent());
            flower.visuals.color = visuals.getElementsByTagName("color").item(0).getTextContent();
            flower.visuals.flower_diameter = Double
                    .parseDouble(visuals.getElementsByTagName("flower_diameter").item(0).getTextContent());
            Element tips = (Element) elem.getElementsByTagName("tips").item(0);
            flower.tips.temp = Double.parseDouble(tips.getElementsByTagName("temperature").item(0).getTextContent());
            flower.tips.light = Integer.parseInt(tips.getElementsByTagName("illumination").item(0).getTextContent());
            flower.tips.water = Integer.parseInt(tips.getElementsByTagName("irrigation").item(0).getTextContent());
            flower.reproduction = Flower.Reproduction
                    .valueOf(elem.getElementsByTagName("reproduction").item(0).getTextContent());
            return flower;
        } else {
            return null;
        }
    }

    private Document getDocument() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlPath);
            return document;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }
}
