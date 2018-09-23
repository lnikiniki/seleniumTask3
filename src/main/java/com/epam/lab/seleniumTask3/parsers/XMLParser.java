package com.epam.lab.seleniumTask3.parsers;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class XMLParser {
    private Document document;

    public XMLParser(String pathToFile) {
        try {
            File file = new File(pathToFile);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getName(String name) {
        return document.getElementsByTagName(name).item(0).getTextContent();
    }
}
