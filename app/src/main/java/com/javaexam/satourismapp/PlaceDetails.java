package com.javaexam.satourismapp;

public class PlaceDetails {
    private int id;
    private String province;
    private String place;

    public PlaceDetails() {

    }
    public PlaceDetails(int id, String province, String place) {
        this.id = id;
        this.province = province;
        this.place = place;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getProvince() {
        return province;
    }

    public String getPlace() {
        return place;
    }
}
