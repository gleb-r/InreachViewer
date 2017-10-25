package com.example.gleb.inreachviewer;

import com.google.android.gms.maps.model.LatLng;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by Gleb on 23.10.2017.
 */

public class InreachKmlParser {
    private static final String TAG = InreachKmlParser.class.getName();


    public static List<LatLng> parse(String kmlStr) throws ParserConfigurationException, IOException, SAXException {
        List<LatLng> latLngPoints = new ArrayList<>();
        // Удаляем символы не соответствующие UTF-8, ниаче ошибка в builder.parse()
        kmlStr = kmlStr.replaceAll("[^\\x20-\\x7e]", "");
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        builderFactory.setNamespaceAware(true);
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        InputSource inputSource = new InputSource(new StringReader(kmlStr));
        Document document = builder.parse(inputSource);
        document.getDocumentElement().normalize();
        NodeList pointsList = document.getElementsByTagName("Point");
        for (int i = 0; i < pointsList.getLength(); i++) {
            NodeList coordinatesList = ((Element) pointsList.item(i)).getElementsByTagName("coordinates");
            for (int j = 0; j < coordinatesList.getLength(); j++) {
                String[] pointStr = coordinatesList.item(j)
                        .getFirstChild()
                        .getNodeValue()
                        .trim()
                        .split(",");
                // широта
                double latitude = Double.valueOf(pointStr[1]);
                // долгота
                double longitude = Double.valueOf(pointStr[0]);
                LatLng point = new LatLng(latitude,longitude);
                latLngPoints.add(point);
            }
        }
        return latLngPoints;
    }
}



