package com.annamukhina.practice.core.bundle;

/**
 * @author anna_mukhina
 */
public class Event {
    private final String date;
    private final String place;
    private final String location;

    public Event(String date, String place, String location) {
        this.date = date;
        this.place = place;
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public String getPlace() {
        return place;
    }

    public String getLocation() {
        return location;
    }
}
