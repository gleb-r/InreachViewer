package com.example.gleb.inreachviewer;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Gleb on 20.10.2017.
 */

public class InreachPoint {

    private long id;
    private String timeUTC;
    private String timeLocal;
    private String name;
    private String mapDisplayName;
    private long imei;
    private LatLng latLng;
    private double elevation;
    private String velocity;
    private float course;
    private boolean inEmergency;
    private String text;
    private String event;

    @Override
    public String toString() {
        String pointStr = "id=" + String.valueOf(id)
                + "\n timeUTC=" + timeUTC
                + "\n timeL=" + timeLocal
                + "\n name=" + name
                + "\n maps disp name=" + mapDisplayName
                + "\n imei=" + imei
                + ", latlng=" + latLng
                + ", elevation=" + elevation
                + ", velocity=" + velocity
                + ", inEmergency=" + inEmergency
                + ", text=" + text
                + ", event=" + event;
        return pointStr;
    }

    public String getTag() {

        String tag = velocity +
                " " + course + " " + elevation + "m";
        return tag;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTimeUTC() {
        return timeUTC;
    }

    public void setTimeUTC(String timeUTC) {
        this.timeUTC = timeUTC;
    }

    public String getTimeLocal() {
        return timeLocal;
    }

    public void setTimeLocal(String timeLocal) {
        this.timeLocal = timeLocal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMapDisplayName() {
        return mapDisplayName;
    }

    public void setMapDisplayName(String mapDisplayName) {
        this.mapDisplayName = mapDisplayName;
    }

    public long getImei() {
        return imei;
    }

    public void setImei(long imei) {
        this.imei = imei;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public double getElevation() {
        return elevation;
    }

    public void setElevation(double elevation) {
        this.elevation = elevation;
    }

    public String getVelocity() {
        return velocity;
    }

    public void setVelocity(String velocity) {
        this.velocity = velocity;
    }

    public float getCourse() {
        return course;
    }

    public void setCourse(float course) {
        this.course = course;
    }

    public boolean isInEmergency() {
        return inEmergency;
    }

    public void setInEmergency(boolean inEmergency) {
        this.inEmergency = inEmergency;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }
}

