package com.example.travelbook.model;

import java.io.Serializable;

public class PlaceModel implements Serializable {

    public String placeName;
    public Double lat;
    public Double lng;

    public PlaceModel(String placeName, Double lat, Double lng) {
        this.placeName = placeName;
        this.lat = lat;
        this.lng = lng;
    }
}
