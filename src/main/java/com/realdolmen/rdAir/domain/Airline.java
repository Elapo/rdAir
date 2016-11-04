package com.realdolmen.rdAir.domain;

import org.hibernate.validator.constraints.URL;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by Frederik Van Herbruggen on 4/11/2016.
 */
@Entity
public class Airline extends User {
    private String airlineName;

    @URL(message = "{validation.url.invalid}")
    private String website;

    @OneToMany
    private List<Object> routes;

    @OneToMany
    private List<Object> flights;

    protected Airline() {
        super();
    }

    public Airline(String firstName, String lastName, String address, String telephone, String email, String airlineName, String website, List<Object> routes, List<Object> flights) {
        super(firstName, lastName, address, telephone, email);
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

    public List<Object> getRoutes() {
        return routes;
    }

    public List<Object> getFlights() {
        return flights;
    }

}
