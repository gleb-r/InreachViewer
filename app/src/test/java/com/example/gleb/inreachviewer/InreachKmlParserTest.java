package com.example.gleb.inreachviewer;

import com.google.android.gms.maps.model.LatLng;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by Gleb on 25.10.2017.
 */

public class InreachKmlParserTest {
    @Test
    public void parseTest () throws Exception{
        String stringXml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<kml xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://www.opengis.net/kml/2.2\">\n" +
                "  <Document>\n" +
                "    <name>KML Export 5/26/2017 2:47:06 PM</name>\n" +
                "    <Style id=\"style_emergency\">\n" +
                "      <IconStyle>\n" +
                "        <colorMode>normal</colorMode>\n" +
                "        <Icon>\n" +
                "          <href>http://maps.google.com/mapfiles/kml/shapes/caution.png</href>\n" +
                "        </Icon>\n" +
                "      </IconStyle>\n" +
                "      <BalloonStyle>\n" +
                "        <text>&lt;table&gt;&lt;tr&gt;&lt;td&gt;Id&lt;/td&gt;&lt;td&gt; $[Id] &lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;Time&lt;/td&gt;&lt;td&gt; $[Time] &lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;Time UTC&lt;/td&gt;&lt;td&gt; $[Time UTC] &lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;Name&lt;/td&gt;&lt;td&gt; $[Name] &lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;Map Display Name&lt;/td&gt;&lt;td&gt; $[Map Display Name] &lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;Device Type&lt;/td&gt;&lt;td&gt; $[Device Type] &lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;IMEI&lt;/td&gt;&lt;td&gt; $[IMEI] &lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;Incident Id&lt;/td&gt;&lt;td&gt; $[Incident Id] &lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;Latitude&lt;/td&gt;&lt;td&gt; $[Latitude] &lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;Longitude&lt;/td&gt;&lt;td&gt; $[Longitude] &lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;Elevation&lt;/td&gt;&lt;td&gt; $[Elevation] &lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;Velocity&lt;/td&gt;&lt;td&gt; $[Velocity] &lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;Course&lt;/td&gt;&lt;td&gt; $[Course] &lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;Valid GPS Fix&lt;/td&gt;&lt;td&gt; $[Valid GPS Fix] &lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;In Emergency&lt;/td&gt;&lt;td&gt; $[In Emergency] &lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;Text&lt;/td&gt;&lt;td&gt; $[Text] &lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;Event&lt;/td&gt;&lt;td&gt; $[Event] &lt;/td&gt;&lt;/tr&gt;&lt;/table&gt;</text>\n" +
                "      </BalloonStyle>\n" +
                "    </Style>\n" +
                "    <Style id=\"style_116571\">\n" +
                "      <IconStyle>\n" +
                "        <color>ffff5500</color>\n" +
                "        <colorMode>normal</colorMode>\n" +
                "        <Icon>\n" +
                "          <href>http://maps.google.com/mapfiles/kml/paddle/wht-blank.png</href>\n" +
                "        </Icon>\n" +
                "      </IconStyle>\n" +
                "      <BalloonStyle>\n" +
                "        <text>&lt;table&gt;&lt;tr&gt;&lt;td&gt;Id&lt;/td&gt;&lt;td&gt; $[Id] &lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;Time&lt;/td&gt;&lt;td&gt; $[Time] &lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;Time UTC&lt;/td&gt;&lt;td&gt; $[Time UTC] &lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;Name&lt;/td&gt;&lt;td&gt; $[Name] &lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;Map Display Name&lt;/td&gt;&lt;td&gt; $[Map Display Name] &lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;Device Type&lt;/td&gt;&lt;td&gt; $[Device Type] &lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;IMEI&lt;/td&gt;&lt;td&gt; $[IMEI] &lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;Incident Id&lt;/td&gt;&lt;td&gt; $[Incident Id] &lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;Latitude&lt;/td&gt;&lt;td&gt; $[Latitude] &lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;Longitude&lt;/td&gt;&lt;td&gt; $[Longitude] &lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;Elevation&lt;/td&gt;&lt;td&gt; $[Elevation] &lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;Velocity&lt;/td&gt;&lt;td&gt; $[Velocity] &lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;Course&lt;/td&gt;&lt;td&gt; $[Course] &lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;Valid GPS Fix&lt;/td&gt;&lt;td&gt; $[Valid GPS Fix] &lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;In Emergency&lt;/td&gt;&lt;td&gt; $[In Emergency] &lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;Text&lt;/td&gt;&lt;td&gt; $[Text] &lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;Event&lt;/td&gt;&lt;td&gt; $[Event] &lt;/td&gt;&lt;/tr&gt;&lt;/table&gt;</text>\n" +
                "      </BalloonStyle>\n" +
                "    </Style>\n" +
                "    <Style id=\"waypointstyle_116571\">\n" +
                "      <IconStyle>\n" +
                "        <color>ffff5500</color>\n" +
                "        <colorMode>normal</colorMode>\n" +
                "        <Icon>\n" +
                "          <href>http://maps.google.com/mapfiles/kml/paddle/wht-blank.png</href>\n" +
                "        </Icon>\n" +
                "      </IconStyle>\n" +
                "      <BalloonStyle>\n" +
                "        <text>&lt;table&gt;&lt;tr&gt;&lt;td&gt;Time&lt;/td&gt;&lt;td&gt; $[Time] &lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;Time UTC&lt;/td&gt;&lt;td&gt; $[Time UTC] &lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;Latitude&lt;/td&gt;&lt;td&gt; $[Latitude] &lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;Longitude&lt;/td&gt;&lt;td&gt; $[Longitude] &lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;Text&lt;/td&gt;&lt;td&gt; $[Text] &lt;/td&gt;&lt;/tr&gt;&lt;/table&gt;</text>\n" +
                "      </BalloonStyle>\n" +
                "    </Style>\n" +
                "    <Style id=\"linestyle_116571\">\n" +
                "      <LineStyle>\n" +
                "        <color>ffff5500</color>\n" +
                "        <colorMode>normal</colorMode>\n" +
                "        <width>1</width>\n" +
                "        <labelVisibility xmlns=\"http://www.google.com/kml/ext/2.2\">false</labelVisibility>\n" +
                "      </LineStyle>\n" +
                "    </Style>\n" +
                "    <Folder>\n" +
                "      <name>Gleb Radolitskiy</name>\n" +
                "      <Placemark>\n" +
                "        <name />\n" +
                "        <visibility>true</visibility>\n" +
                "        <description />\n" +
                "        <TimeStamp>\n" +
                "          <when>2017-05-26T13:12:00Z</when>\n" +
                "        </TimeStamp>\n" +
                "        <styleUrl>#style_116571</styleUrl>\n" +
                "        <ExtendedData>\n" +
                "          <Data name=\"Id\">\n" +
                "            <value>125821557</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Time UTC\">\n" +
                "            <value>5/26/2017 1:12:00 PM</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Time\">\n" +
                "            <value>5/26/2017 4:12:00 PM</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Name\">\n" +
                "            <value>Gleb Radolitskiy</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Map Display Name\">\n" +
                "            <value>Gleb Radolitskiy</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Device Type\">\n" +
                "            <value>inReach 2.5</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"IMEI\">\n" +
                "            <value>300434060198600</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Incident Id\">\n" +
                "            <value />\n" +
                "          </Data>\n" +
                "          <Data name=\"Latitude\">\n" +
                "            <value>59.726818</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Longitude\">\n" +
                "            <value>29.638323</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Elevation\">\n" +
                "            <value>142.87 m from MSL</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Velocity\">\n" +
                "            <value>0.0 km/h</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Course\">\n" +
                "            <value>112.50 ° True</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Valid GPS Fix\">\n" +
                "            <value>True</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"In Emergency\">\n" +
                "            <value>False</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Text\">\n" +
                "            <value />\n" +
                "          </Data>\n" +
                "          <Data name=\"Event\">\n" +
                "            <value>Tracking turned on from device.</value>\n" +
                "          </Data>\n" +
                "        </ExtendedData>\n" +
                "        <Point>\n" +
                "          <extrude>false</extrude>\n" +
                "          <altitudeMode>absolute</altitudeMode>\n" +
                "          <coordinates>29.638323,59.726818,142.87</coordinates>\n" +
                "        </Point>\n" +
                "      </Placemark>\n" +
                "      <Placemark>\n" +
                "        <name />\n" +
                "        <visibility>true</visibility>\n" +
                "        <description />\n" +
                "        <TimeStamp>\n" +
                "          <when>2017-05-26T13:22:00Z</when>\n" +
                "        </TimeStamp>\n" +
                "        <styleUrl>#style_116571</styleUrl>\n" +
                "        <ExtendedData>\n" +
                "          <Data name=\"Id\">\n" +
                "            <value>125823001</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Time UTC\">\n" +
                "            <value>5/26/2017 1:22:00 PM</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Time\">\n" +
                "            <value>5/26/2017 4:22:00 PM</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Name\">\n" +
                "            <value>Gleb Radolitskiy</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Map Display Name\">\n" +
                "            <value>Gleb Radolitskiy</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Device Type\">\n" +
                "            <value>inReach 2.5</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"IMEI\">\n" +
                "            <value>300434060198600</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Incident Id\">\n" +
                "            <value />\n" +
                "          </Data>\n" +
                "          <Data name=\"Latitude\">\n" +
                "            <value>59.726733</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Longitude\">\n" +
                "            <value>29.638195</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Elevation\">\n" +
                "            <value>140.82 m from MSL</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Velocity\">\n" +
                "            <value>0.0 km/h</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Course\">\n" +
                "            <value>0.00 ° True</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Valid GPS Fix\">\n" +
                "            <value>True</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"In Emergency\">\n" +
                "            <value>False</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Text\">\n" +
                "            <value />\n" +
                "          </Data>\n" +
                "          <Data name=\"Event\">\n" +
                "            <value>Tracking interval received.</value>\n" +
                "          </Data>\n" +
                "        </ExtendedData>\n" +
                "        <Point>\n" +
                "          <extrude>false</extrude>\n" +
                "          <altitudeMode>absolute</altitudeMode>\n" +
                "          <coordinates>29.638195,59.726733,140.82</coordinates>\n" +
                "        </Point>\n" +
                "      </Placemark>\n" +
                "      <Placemark>\n" +
                "        <name />\n" +
                "        <visibility>true</visibility>\n" +
                "        <description />\n" +
                "        <TimeStamp>\n" +
                "          <when>2017-05-26T13:36:00Z</when>\n" +
                "        </TimeStamp>\n" +
                "        <styleUrl>#style_116571</styleUrl>\n" +
                "        <ExtendedData>\n" +
                "          <Data name=\"Id\">\n" +
                "            <value>125824742</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Time UTC\">\n" +
                "            <value>5/26/2017 1:36:00 PM</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Time\">\n" +
                "            <value>5/26/2017 4:36:00 PM</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Name\">\n" +
                "            <value>Gleb Radolitskiy</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Map Display Name\">\n" +
                "            <value>Gleb Radolitskiy</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Device Type\">\n" +
                "            <value>inReach 2.5</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"IMEI\">\n" +
                "            <value>300434060198600</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Incident Id\">\n" +
                "            <value />\n" +
                "          </Data>\n" +
                "          <Data name=\"Latitude\">\n" +
                "            <value>59.725113</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Longitude\">\n" +
                "            <value>29.640148</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Elevation\">\n" +
                "            <value>136.73 m from MSL</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Velocity\">\n" +
                "            <value>26.5 km/h</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Course\">\n" +
                "            <value>157.50 ° True</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Valid GPS Fix\">\n" +
                "            <value>True</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"In Emergency\">\n" +
                "            <value>False</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Text\">\n" +
                "            <value />\n" +
                "          </Data>\n" +
                "          <Data name=\"Event\">\n" +
                "            <value>Tracking interval received.</value>\n" +
                "          </Data>\n" +
                "        </ExtendedData>\n" +
                "        <Point>\n" +
                "          <extrude>false</extrude>\n" +
                "          <altitudeMode>absolute</altitudeMode>\n" +
                "          <coordinates>29.640148,59.725113,136.73</coordinates>\n" +
                "        </Point>\n" +
                "      </Placemark>\n" +
                "      <Placemark>\n" +
                "        <name />\n" +
                "        <visibility>true</visibility>\n" +
                "        <description />\n" +
                "        <TimeStamp>\n" +
                "          <when>2017-05-26T13:46:00Z</when>\n" +
                "        </TimeStamp>\n" +
                "        <styleUrl>#style_116571</styleUrl>\n" +
                "        <ExtendedData>\n" +
                "          <Data name=\"Id\">\n" +
                "            <value>125825681</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Time UTC\">\n" +
                "            <value>5/26/2017 1:46:00 PM</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Time\">\n" +
                "            <value>5/26/2017 4:46:00 PM</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Name\">\n" +
                "            <value>Gleb Radolitskiy</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Map Display Name\">\n" +
                "            <value>Gleb Radolitskiy</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Device Type\">\n" +
                "            <value>inReach 2.5</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"IMEI\">\n" +
                "            <value>300434060198600</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Incident Id\">\n" +
                "            <value />\n" +
                "          </Data>\n" +
                "          <Data name=\"Latitude\">\n" +
                "            <value>59.723975</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Longitude\">\n" +
                "            <value>29.639396</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Elevation\">\n" +
                "            <value>126.51 m from MSL</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Velocity\">\n" +
                "            <value>0.0 km/h</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Course\">\n" +
                "            <value>0.00 ° True</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Valid GPS Fix\">\n" +
                "            <value>True</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"In Emergency\">\n" +
                "            <value>False</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Text\">\n" +
                "            <value />\n" +
                "          </Data>\n" +
                "          <Data name=\"Event\">\n" +
                "            <value>Tracking message received.</value>\n" +
                "          </Data>\n" +
                "        </ExtendedData>\n" +
                "        <Point>\n" +
                "          <extrude>false</extrude>\n" +
                "          <altitudeMode>absolute</altitudeMode>\n" +
                "          <coordinates>29.639396,59.723975,126.51</coordinates>\n" +
                "        </Point>\n" +
                "      </Placemark>\n" +
                "      <Placemark>\n" +
                "        <name />\n" +
                "        <visibility>true</visibility>\n" +
                "        <description />\n" +
                "        <TimeStamp>\n" +
                "          <when>2017-05-26T13:56:00Z</when>\n" +
                "        </TimeStamp>\n" +
                "        <styleUrl>#style_116571</styleUrl>\n" +
                "        <ExtendedData>\n" +
                "          <Data name=\"Id\">\n" +
                "            <value>125827342</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Time UTC\">\n" +
                "            <value>5/26/2017 1:56:00 PM</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Time\">\n" +
                "            <value>5/26/2017 4:56:00 PM</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Name\">\n" +
                "            <value>Gleb Radolitskiy</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Map Display Name\">\n" +
                "            <value>Gleb Radolitskiy</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Device Type\">\n" +
                "            <value>inReach 2.5</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"IMEI\">\n" +
                "            <value>300434060198600</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Incident Id\">\n" +
                "            <value />\n" +
                "          </Data>\n" +
                "          <Data name=\"Latitude\">\n" +
                "            <value>59.734833</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Longitude\">\n" +
                "            <value>29.378385</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Elevation\">\n" +
                "            <value>520.88 m from MSL</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Velocity\">\n" +
                "            <value>126.5 km/h</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Course\">\n" +
                "            <value>270.00 ° True</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Valid GPS Fix\">\n" +
                "            <value>True</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"In Emergency\">\n" +
                "            <value>False</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Text\">\n" +
                "            <value />\n" +
                "          </Data>\n" +
                "          <Data name=\"Event\">\n" +
                "            <value>Tracking message received.</value>\n" +
                "          </Data>\n" +
                "        </ExtendedData>\n" +
                "        <Point>\n" +
                "          <extrude>false</extrude>\n" +
                "          <altitudeMode>absolute</altitudeMode>\n" +
                "          <coordinates>29.378385,59.734833,520.88</coordinates>\n" +
                "        </Point>\n" +
                "      </Placemark>\n" +
                "      <Placemark>\n" +
                "        <name />\n" +
                "        <visibility>true</visibility>\n" +
                "        <description />\n" +
                "        <TimeStamp>\n" +
                "          <when>2017-05-26T14:06:00Z</when>\n" +
                "        </TimeStamp>\n" +
                "        <styleUrl>#style_116571</styleUrl>\n" +
                "        <ExtendedData>\n" +
                "          <Data name=\"Id\">\n" +
                "            <value>125828258</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Time UTC\">\n" +
                "            <value>5/26/2017 2:06:00 PM</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Time\">\n" +
                "            <value>5/26/2017 5:06:00 PM</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Name\">\n" +
                "            <value>Gleb Radolitskiy</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Map Display Name\">\n" +
                "            <value>Gleb Radolitskiy</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Device Type\">\n" +
                "            <value>inReach 2.5</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"IMEI\">\n" +
                "            <value>300434060198600</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Incident Id\">\n" +
                "            <value />\n" +
                "          </Data>\n" +
                "          <Data name=\"Latitude\">\n" +
                "            <value>59.686081</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Longitude\">\n" +
                "            <value>29.102955</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Elevation\">\n" +
                "            <value>482.91 m from MSL</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Velocity\">\n" +
                "            <value>135.2 km/h</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Course\">\n" +
                "            <value>247.50 ° True</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Valid GPS Fix\">\n" +
                "            <value>True</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"In Emergency\">\n" +
                "            <value>False</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Text\">\n" +
                "            <value />\n" +
                "          </Data>\n" +
                "          <Data name=\"Event\">\n" +
                "            <value>Tracking message received.</value>\n" +
                "          </Data>\n" +
                "        </ExtendedData>\n" +
                "        <Point>\n" +
                "          <extrude>false</extrude>\n" +
                "          <altitudeMode>absolute</altitudeMode>\n" +
                "          <coordinates>29.102955,59.686081,482.91</coordinates>\n" +
                "        </Point>\n" +
                "      </Placemark>\n" +
                "      <Placemark>\n" +
                "        <name />\n" +
                "        <visibility>true</visibility>\n" +
                "        <description />\n" +
                "        <TimeStamp>\n" +
                "          <when>2017-05-26T14:16:00Z</when>\n" +
                "        </TimeStamp>\n" +
                "        <styleUrl>#style_116571</styleUrl>\n" +
                "        <ExtendedData>\n" +
                "          <Data name=\"Id\">\n" +
                "            <value>125829606</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Time UTC\">\n" +
                "            <value>5/26/2017 2:16:00 PM</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Time\">\n" +
                "            <value>5/26/2017 5:16:00 PM</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Name\">\n" +
                "            <value>Gleb Radolitskiy</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Map Display Name\">\n" +
                "            <value>Gleb Radolitskiy</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Device Type\">\n" +
                "            <value>inReach 2.5</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"IMEI\">\n" +
                "            <value>300434060198600</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Incident Id\">\n" +
                "            <value />\n" +
                "          </Data>\n" +
                "          <Data name=\"Latitude\">\n" +
                "            <value>59.605508</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Longitude\">\n" +
                "            <value>28.974510</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Elevation\">\n" +
                "            <value>386.52 m from MSL</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Velocity\">\n" +
                "            <value>138.1 km/h</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Course\">\n" +
                "            <value>337.50 ° True</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Valid GPS Fix\">\n" +
                "            <value>True</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"In Emergency\">\n" +
                "            <value>False</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Text\">\n" +
                "            <value />\n" +
                "          </Data>\n" +
                "          <Data name=\"Event\">\n" +
                "            <value>Tracking message received.</value>\n" +
                "          </Data>\n" +
                "        </ExtendedData>\n" +
                "        <Point>\n" +
                "          <extrude>false</extrude>\n" +
                "          <altitudeMode>absolute</altitudeMode>\n" +
                "          <coordinates>28.97451,59.605508,386.52</coordinates>\n" +
                "        </Point>\n" +
                "      </Placemark>\n" +
                "      <Placemark>\n" +
                "        <name />\n" +
                "        <visibility>true</visibility>\n" +
                "        <description />\n" +
                "        <TimeStamp>\n" +
                "          <when>2017-05-26T14:26:00Z</when>\n" +
                "        </TimeStamp>\n" +
                "        <styleUrl>#style_116571</styleUrl>\n" +
                "        <ExtendedData>\n" +
                "          <Data name=\"Id\">\n" +
                "            <value>125831115</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Time UTC\">\n" +
                "            <value>5/26/2017 2:26:00 PM</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Time\">\n" +
                "            <value>5/26/2017 5:26:00 PM</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Name\">\n" +
                "            <value>Gleb Radolitskiy</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Map Display Name\">\n" +
                "            <value>Gleb Radolitskiy</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Device Type\">\n" +
                "            <value>inReach 2.5</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"IMEI\">\n" +
                "            <value>300434060198600</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Incident Id\">\n" +
                "            <value />\n" +
                "          </Data>\n" +
                "          <Data name=\"Latitude\">\n" +
                "            <value>59.667575</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Longitude\">\n" +
                "            <value>28.994336</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Elevation\">\n" +
                "            <value>224.94 m from MSL</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Velocity\">\n" +
                "            <value>160.3 km/h</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Course\">\n" +
                "            <value>22.50 ° True</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Valid GPS Fix\">\n" +
                "            <value>True</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"In Emergency\">\n" +
                "            <value>False</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Text\">\n" +
                "            <value />\n" +
                "          </Data>\n" +
                "          <Data name=\"Event\">\n" +
                "            <value>Tracking message received.</value>\n" +
                "          </Data>\n" +
                "        </ExtendedData>\n" +
                "        <Point>\n" +
                "          <extrude>false</extrude>\n" +
                "          <altitudeMode>absolute</altitudeMode>\n" +
                "          <coordinates>28.994336,59.667575,224.94</coordinates>\n" +
                "        </Point>\n" +
                "      </Placemark>\n" +
                "      <Placemark>\n" +
                "        <name />\n" +
                "        <visibility>true</visibility>\n" +
                "        <description />\n" +
                "        <TimeStamp>\n" +
                "          <when>2017-05-26T14:36:00Z</when>\n" +
                "        </TimeStamp>\n" +
                "        <styleUrl>#style_116571</styleUrl>\n" +
                "        <ExtendedData>\n" +
                "          <Data name=\"Id\">\n" +
                "            <value>125832785</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Time UTC\">\n" +
                "            <value>5/26/2017 2:36:00 PM</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Time\">\n" +
                "            <value>5/26/2017 5:36:00 PM</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Name\">\n" +
                "            <value>Gleb Radolitskiy</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Map Display Name\">\n" +
                "            <value>Gleb Radolitskiy</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Device Type\">\n" +
                "            <value>inReach 2.5</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"IMEI\">\n" +
                "            <value>300434060198600</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Incident Id\">\n" +
                "            <value />\n" +
                "          </Data>\n" +
                "          <Data name=\"Latitude\">\n" +
                "            <value>59.657413</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Longitude\">\n" +
                "            <value>28.998606</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Elevation\">\n" +
                "            <value>102.01 m from MSL</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Velocity\">\n" +
                "            <value>0.0 km/h</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Course\">\n" +
                "            <value>0.00 ° True</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Valid GPS Fix\">\n" +
                "            <value>True</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"In Emergency\">\n" +
                "            <value>False</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Text\">\n" +
                "            <value />\n" +
                "          </Data>\n" +
                "          <Data name=\"Event\">\n" +
                "            <value>Tracking message received.</value>\n" +
                "          </Data>\n" +
                "        </ExtendedData>\n" +
                "        <Point>\n" +
                "          <extrude>false</extrude>\n" +
                "          <altitudeMode>absolute</altitudeMode>\n" +
                "          <coordinates>28.998606,59.657413,102.01</coordinates>\n" +
                "        </Point>\n" +
                "      </Placemark>\n" +
                "      <Placemark>\n" +
                "        <name>Gleb Radolitskiy</name>\n" +
                "        <visibility>true</visibility>\n" +
                "        <description />\n" +
                "        <TimeStamp>\n" +
                "          <when>2017-05-26T14:46:00Z</when>\n" +
                "        </TimeStamp>\n" +
                "        <styleUrl>#style_116571</styleUrl>\n" +
                "        <ExtendedData>\n" +
                "          <Data name=\"Id\">\n" +
                "            <value>125834198</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Time UTC\">\n" +
                "            <value>5/26/2017 2:46:00 PM</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Time\">\n" +
                "            <value>5/26/2017 5:46:00 PM</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Name\">\n" +
                "            <value>Gleb Radolitskiy</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Map Display Name\">\n" +
                "            <value>Gleb Radolitskiy</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Device Type\">\n" +
                "            <value>inReach 2.5</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"IMEI\">\n" +
                "            <value>300434060198600</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Incident Id\">\n" +
                "            <value />\n" +
                "          </Data>\n" +
                "          <Data name=\"Latitude\">\n" +
                "            <value>59.654313</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Longitude\">\n" +
                "            <value>28.994808</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Elevation\">\n" +
                "            <value>97.93 m from MSL</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Velocity\">\n" +
                "            <value>0.0 km/h</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Course\">\n" +
                "            <value>0.00 ° True</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Valid GPS Fix\">\n" +
                "            <value>True</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"In Emergency\">\n" +
                "            <value>False</value>\n" +
                "          </Data>\n" +
                "          <Data name=\"Text\">\n" +
                "            <value />\n" +
                "          </Data>\n" +
                "          <Data name=\"Event\">\n" +
                "            <value>Tracking message received.</value>\n" +
                "          </Data>\n" +
                "        </ExtendedData>\n" +
                "        <Point>\n" +
                "          <extrude>false</extrude>\n" +
                "          <altitudeMode>absolute</altitudeMode>\n" +
                "          <coordinates>28.994808,59.654313,97.93</coordinates>\n" +
                "        </Point>\n" +
                "      </Placemark>\n" +
                "      <Placemark>\n" +
                "        <name>Gleb Radolitskiy</name>\n" +
                "        <visibility>true</visibility>\n" +
                "        <description>Gleb Radolitskiy's track log</description>\n" +
                "        <styleUrl>#linestyle_116571</styleUrl>\n" +
                "        <LineString>\n" +
                "          <tessellate>true</tessellate>\n" +
                "          <coordinates>29.638323,59.726818,142.87\n" +
                "29.638195,59.726733,140.82\n" +
                "29.640148,59.725113,136.73\n" +
                "29.639396,59.723975,126.51\n" +
                "29.378385,59.734833,520.88\n" +
                "29.102955,59.686081,482.91\n" +
                "28.97451,59.605508,386.52\n" +
                "28.994336,59.667575,224.94\n" +
                "28.998606,59.657413,102.01\n" +
                "28.994808,59.654313,97.93</coordinates>\n" +
                "        </LineString>\n" +
                "      </Placemark>\n" +
                "    </Folder>\n" +
                "  </Document>\n" +
                "</kml>";
        List<LatLng> points = InreachKmlParser.parse(stringXml);
        LatLng expectedPoint = new LatLng(59.654313, 28.994808);
        Assert.assertEquals(expectedPoint, points.get(points.size()-1));
    }
}
