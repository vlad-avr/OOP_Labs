package com.example.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
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
        private String name;
        private String type;
        private List<SchemaElement> children;
        private Map<String, String> attributes;

        public void setName(String value) {
            this.name = value;
        }

        public void setType(String value) {
            this.type = value;
        }

        public Object getType() {
            return type;
        }

        public void setChildren(List<SchemaElement> children2) {
        }

        public void setAttributes(Object attributes2) {
        }

        public String getName() {
            return name;
        }

        public Map<String, String> getAttributes() {
            return attributes;
        }

        public List<SchemaElement> getChildren() {
            return children;
        }

    }

    class SchemaComplexType {
        private String name;
        private List<SchemaElement> children;
        private Map<String, String> attributes = new HashMap<>();

        public void setName(String value) {
            this.name = value;
        }

        public void addAttribute(String key, String value) {
            attributes.put(key, value);
        }

        public String getName() {
            return name;
        }

        public void setChildren(List<SchemaElement> currentSequence) {
            this.children = currentSequence;
        }

        public List<SchemaElement> getChildren() {
            return children;
        }

        public Object getAttributes() {
            return attributes;
        }

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

        @Override
        public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {

            if (qName.equals("xs:simpleType")) {
                currentSimpleTypeName = atts.getValue("name");
            }
            if (qName.equals("xs:restriction")) {
                currentSimpleTypeBaseType = atts.getValue("base");
            }

            if (qName.equals("xs:complexType")) {
                currentComplexType = new SchemaComplexType();
                currentComplexType.setName(atts.getValue("name"));
            }

            if (qName.equals("xs:sequence")) {
                currentSequence = new ArrayList<>();
            }

            if (qName.equals("xs:element")) {
                currentElement = new SchemaElement();
                currentElement.setName(atts.getValue("name"));
                currentElement.setType(atts.getValue("type"));
                if (currentSequence != null) {
                    currentSequence.add(currentElement);
                } else {
                    rootElement = currentElement;
                }
            }

            if (qName.equals("xs:attribute")) {
                currentComplexType.addAttribute(atts.getValue("name"), atts.getValue("type"));
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if (qName.equals("xs:simpleType")) {
                simpleTypes.put(currentSimpleTypeName, currentSimpleTypeBaseType);
                currentSimpleTypeName = null;
                currentSimpleTypeBaseType = null;
            }
            if (qName.equals("xs:complexType")) {
                complexTypes.put(currentComplexType.getName(), currentComplexType);
                currentComplexType = null;
            }
            if (qName.equals("xs:sequence")) {
                if (currentComplexType != null) {
                    currentComplexType.setChildren(currentSequence);
                }
                currentSequence = null;
            }
        }

        @Override
        public void endDocument() throws SAXException {
            makeTree(rootElement);
            printTree(rootElement, "");
        }

        public void makeTree(SchemaElement element) {
            SchemaComplexType type = complexTypes.get(element.getType());
            if (type != null) {
                List<SchemaElement> children = type.getChildren();
                element.setChildren(children);
                for (SchemaElement child : children) {
                    makeTree(child);
                }
                element.setAttributes(type.getAttributes());
            } else {
                element.setType(simpleTypes.get(element.getType()));
            }
        }

        private void printTree(SchemaElement element, String indent) {
            System.out.println(indent + element.getName() + " : " + element.getType());
            Map<String, String> attributes = element.getAttributes();
            if (attributes != null) {
                for (Map.Entry<String, String> entry : attributes.entrySet()) {
                    System.out.println("    @" + entry.getKey() + " : " + simpleTypes.get(entry.getValue()));
                }
            }
            List<SchemaElement> children = element.getChildren();
            if (children != null) {
                for (SchemaElement child : children) {
                    printTree(child, indent + "    ");
                }
            }
        }
    }
}
