package com.annamukhina.practice.core.bundle;

/**
 * @author anna_mukhina
 */
public class Concert {
    private String date;
    private String place;
    private String city;
    private double geoLong;
    private double geoLat;

    public Concert() {}

    public Concert(String date, String place, String city, double geoLong, double geoLat) {
        this.date = date;
        this.place = place;
        this.city = city;
        this.geoLong = geoLong;
        this.geoLat = geoLat;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getGeoLong() {
        return geoLong;
    }

    public void setGeoLong(double geoLong) {
        this.geoLong = geoLong;
    }

    public double getGeoLat() {
        return geoLat;
    }

    public void setGeoLat(double geoLat) {
        this.geoLat = geoLat;
    }
}
