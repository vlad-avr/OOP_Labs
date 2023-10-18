package com.example;

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
        // DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        // DocumentBuilder builder;
        // try {
        //     builder = factory.newDocumentBuilder();
        //     org.w3c.dom.Document doc = builder.parse(new File("src\\main\\java\\com\\example\\Greenhouse.xml"));
        //     NodeList flowerList = doc.getElementsByTagName("flower");
        //     for(int i = 0; i < flowerList.getLength(); i++){
        //         Node flower = flowerList.item(i);
        //         if(flower.getNodeType() == Node.ELEMENT_NODE){
        //             Element flowerElement = (Element)flower;
        //             System.out.println("Name : " + flowerElement.getAttribute("name"));
        //             NodeList attributes = flower.getChildNodes();
        //             for(int j = 0; j < attributes.getLength(); j++){
        //                 Node atr = attributes.item(j);
        //                 if(atr.getNodeType() == Node.ELEMENT_NODE){
        //                     Element attrElement = (Element)atr;
        //                     System.out.println("\t" + attrElement.getTagName() + " : " + attrElement.getAttribute("value"));
        //                 }
        //             }
        //         }
        //     }
        // } catch (ParserConfigurationException | SAXException | IOException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }
        XMLParser parser = new XMLParser("Greenhouse.xml");

    }
}
