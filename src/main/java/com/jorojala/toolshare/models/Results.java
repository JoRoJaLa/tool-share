package com.jorojala.toolshare.models;

import javax.persistence.*;

@Entity
@Table(name = "results")
public class Results {
    double lat;
    double lon;
    String city;
    String postcode;
    String state;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public Long id;

    @OneToOne(mappedBy = "results")
    public AppUser appUser;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }




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
