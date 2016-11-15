package com.realdolmen.rdAir.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Frederik Van Herbruggen on 4/11/2016.
 */
@Entity
public class Location implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String airportName;

    private String airportCode;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Region region;

    public Location() {
    }

    public Location(String airportName, String airportCode, Region region) {
        this.airportName = airportName;
        this.airportCode = airportCode;
        this.region = region;
    }

    public int getId() {
        return id;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    @Override
    public String toString(){
        return airportCode + ": " + airportName + " (" +region.getName()+")";
    }
}
