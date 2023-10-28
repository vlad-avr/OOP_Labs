package com.example.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import com.example.flowers.Greenhouse;

public class MySAXParser {

    public Greenhouse parse(String filePath) {
        Greenhouse greenhouse = null;
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            XMLReader reader = parser.getXMLReader();
            reader.setContentHandler(new SchemaHandler());
            reader.parse(new InputSource(new FileInputStream(new File(filePath))));
        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.out.println(e.getMessage());
        }
        return greenhouse;
    }

    class SchemaElement {

    }

    class SchemaComplexType {

    }

    class SchemaHandler extends DefaultHandler {
        private String currentSimpleTypeName;
        private String currentSimpleTypeBaseType;
        private SchemaElement currentElement;
        private SchemaComplexType currentComplexType;
        private List<SchemaElement> currentSequence;

        private Map<String, String> simpleTypes = new HashMap<>();
        private Map<String, SchemaComplexType> complexTypes = new HashMap<>();
        private SchemaElement rootElement;
    }
}
