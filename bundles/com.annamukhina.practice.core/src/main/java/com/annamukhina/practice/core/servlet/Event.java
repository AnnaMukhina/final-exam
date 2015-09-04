package com.annamukhina.practice.core.servlet;

/**
 * @author anna_mukhina
 */

public class Event {
    private String id;
    private String date;
    private String place;
    private String city;
    private float latitude;
    private float longitude;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Event [id=" + id + ", date=" + date + ", " + "place=" + place
                + "city" + city + "longitude" + longitude + "latitude" + latitude + "]";
    }
}
