package com.annamukhina.practice.core.servlet;

/**
 * @author anna_mukhina
 */

public class Event {
    private String id;
    private String date;
    private String place;
    private String city;
    private String latitude;
    private String longitude;

    public Event(String id, String date, String place, String city, String latitude, String longitude) {
        this.id = id;
        this.date = date;
        this.place = place;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Event() {
    }

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

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Event [id=" + id + ", date=" + date + ", " + "place=" + place
                + "city" + city + "longitude" + longitude + "latitude" + latitude + "]";
    }
}
