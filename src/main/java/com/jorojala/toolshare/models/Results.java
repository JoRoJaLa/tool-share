package com.jorojala.toolshare.models;

public class Results {
    double lat;
    double lon;
    String city;
    String postcode;
    String state;


    @Override
    public String toString() {
        return "Results{" +
                "lat=" + lat +
                ", lon=" + lon +
                ", city='" + city + '\'' +
                ", postcode='" + postcode + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
