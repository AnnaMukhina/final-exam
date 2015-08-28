package com.annamukhina.practice.core.bundle;

/**
 * @author anna_mukhina
 */
public class Concert {
    private String date;
    private String place;
    private String location;

    public Concert() {}

    public Concert(String date, String place, String location) {
        this.date = date;
        this.place = place;
        this.location = location;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setLocation(String location) {
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
