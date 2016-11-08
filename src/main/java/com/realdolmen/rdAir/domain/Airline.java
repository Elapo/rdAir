package com.realdolmen.rdAir.domain;

import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Frederik Van Herbruggen on 4/11/2016.
 */
@Entity
public class Airline extends User implements Serializable{
    private String airlineName;

    @URL(message = "{validation.url.invalid}")
    private String website;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Route> routes;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Flight> flights;

    protected Airline() {
        super();
        this.routes = new ArrayList<>();
        this.flights = new ArrayList<>();
    }

    public Airline(String firstName, String lastName, String address, String telephone, String email, String airlineName, String website, String passwordHash, List<Route> routes, List<Flight> flights) {
        super(firstName, lastName, address, telephone, email, passwordHash);
        this.routes = new ArrayList<>();
        this.flights = new ArrayList<>();
        this.airlineName = airlineName;
        this.website = website;
        this.routes = routes;
        this.flights = flights;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public List<Flight> getFlights() {
        return flights;
    }

}
