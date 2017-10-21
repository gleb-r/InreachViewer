package com.example.gleb.inreachviewer;

import android.location.Location;

import java.util.Date;

/**
 * Created by Gleb on 20.10.2017.
 */

public class InreachPoint {

    private long id;
    private Date timeUTC;
    private Date timeLocal;
    private String name;
    private long imei;
    private Location location;
    private boolean inEmergency;
    private String text;
    private String event;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getTimeUTC() {
        return timeUTC;
    }

    public void setTimeUTC(Date timeUTC) {
        this.timeUTC = timeUTC;
    }

    public Date getTimeLocal() {
        return timeLocal;
    }

    public void setTimeLocal(Date timeLocal) {
        this.timeLocal = timeLocal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getImei() {
        return imei;
    }

    public void setImei(long imei) {
        this.imei = imei;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
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

