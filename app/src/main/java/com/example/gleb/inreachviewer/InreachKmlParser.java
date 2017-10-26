package com.example.gleb.inreachviewer;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by Gleb on 23.10.2017.
 */

public class InreachKmlParser {
    private static final String TAG = InreachKmlParser.class.getName();


    public static List<InreachPoint> parse(String kmlStr)
            throws ParserConfigurationException, IOException, SAXException, ParseException {
        List<InreachPoint> inreachPoints = new ArrayList<>();
        // Удаляем символы не соответствующие UTF-8, ниаче ошибка в builder.parse()
        kmlStr = kmlStr.replaceAll("[^\\x20-\\x7e]", "");
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        builderFactory.setNamespaceAware(true);
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        InputSource inputSource = new InputSource(new StringReader(kmlStr));
        Document document = builder.parse(inputSource);
        document.getDocumentElement().normalize();
        NodeList pointsList = document.getElementsByTagName("Placemark");
        Log.i(TAG, "placemark lenght=" + pointsList.getLength());
        for (int i = 0; i < pointsList.getLength(); i++) {
            InreachPoint point = parsePoint(pointsList.item(i));
            if (point != null) {
                inreachPoints.add(point);
            } else {
                Log.i(TAG, "point =null");
            }
        }
        return inreachPoints;
    }

    private static InreachPoint parsePoint(Node placemarkNode) throws ParseException {
        InreachPoint point = new InreachPoint();
        //Raw Point data
        NodeList pointNodeList = ((Element) placemarkNode).getElementsByTagName("Point");
        if (pointNodeList.getLength() != 1) {
            NodeList lineStringNode = ((Element) placemarkNode).getElementsByTagName("LineString");
            if (lineStringNode.getLength() != 1) {
                return null;
            }
            Node lineCoordinatesNode = ((Element) lineStringNode.item(0)).getElementsByTagName("coordinates").item(0);
            String lineCoorinates = lineCoordinatesNode.getTextContent().trim();
            Log.i(TAG, "lineCoordinates= " + lineCoorinates);
            return null;
        }
        NodeList coordinatesList = ((Element) pointNodeList.item(0))
                .getElementsByTagName("coordinates");
        if (coordinatesList.getLength() != 1) {
            return null;
        }
        String[] coordinateStr = coordinatesList
                .item(0)
                .getFirstChild()
                .getNodeValue()
                .trim()
                .split(",");
        // широта
        double latitude = Double.valueOf(coordinateStr[1]);
        // долгота
        double longitude = Double.valueOf(coordinateStr[0]);
        // высота абсалютная
        double elevation = Double.valueOf(coordinateStr[2]);
        point.setLatLng(new LatLng(latitude, longitude));
        point.setElevation(elevation);

        //ExternalData
        NodeList externalNodeList = ((Element) placemarkNode)
                .getElementsByTagName("ExtendedData");
        Log.i(TAG, "externalNodeList length =" + externalNodeList.getLength());
        if (externalNodeList.getLength() != 1) {
            return null;
        }
        NodeList dataNodeList = ((Element) externalNodeList.item(0))
                .getElementsByTagName("Data");
        Log.i(TAG, "dataNodeList length =" + dataNodeList.getLength());
        if (dataNodeList.getLength() == 0) {
            return null;
        }
        for (int i = 0; i < dataNodeList.getLength(); i++) {
            Node dataNode = dataNodeList.item(i);
            Node attrbuteNode = dataNode
                    .getAttributes()
                    .getNamedItem("name");
            if (attrbuteNode == null) {
                Log.i(TAG, "attebute=" + attrbuteNode);
                return null;
            }
            String dataAttributeName = attrbuteNode.getNodeValue().trim();
            String dataValue = dataNode.getTextContent().trim();
            switch (dataAttributeName) {
                case "Id":
                    point.setId(Long.valueOf(dataValue));
                    break;
                case "Time UTC":
                    point.setTimeUTC(dataValue);
                    break;
                case "Time":
                    point.setTimeLocal(dataValue);
                    break;
                case "Name":
                    point.setName(dataValue);
                    break;
                case "IMEI":
                    point.setImei(Long.valueOf(dataValue));
                    break;
                case "Velocity":
                    point.setVelocity(dataValue);
                    break;
                case "Course":
                    String[] course = dataValue.split(" ");
                    point.setCourse(course[0]+"°");
                    break;
                case "In Emergency":
                    point.setInEmergency(parseBoolValue(dataValue));
                    break;
                case "Text":
                    point.setText(dataValue);
                    break;
                case "Event":
                    point.setEvent(dataValue);
                    break;
                case "Map Display Name":
                    point.setMapDisplayName(dataValue);
                    break;
                case "Device Type":
                    break;
                case "Incident Id":
                    break;
                case "Latitude":
                    break;
                case "Longitude":
                    break;
                case "Elevation":
                    break;
                case "Valid GPS Fix":
                    break;
                default:
                    return null;
            }
        }
        return point;
    }

    private static Date parseDateValue(String dateString) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat
                ("d/MM/y h:mm:ss a", Locale.US);
        return dateFormat.parse(dateString);
    }

    private static boolean parseBoolValue(String boolString) {
        return boolString.equals("True") || boolString.equals("1");
    }
}



