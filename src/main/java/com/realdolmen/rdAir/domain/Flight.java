package com.realdolmen.rdAir.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Frederik Van Herbruggen on 4/11/2016.
 */
@Entity
public class Flight implements Serializable{

        /*
    - seatcount
    - class
    - Airline
    - departure/arrival, Region
    - Date
    - ReturnDate
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Route route;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private PriceModifier rdAirModifier;

    @Temporal(TemporalType.DATE)
    private Date departureTime;

    @Temporal(TemporalType.TIME)
    private Date flightDuration;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.PERSIST)
    private List<FlightClass> availableClasses;

    protected Flight() {
        super();
    }

    public Flight(Route route, PriceModifier rdAirModifier, Date departureTime, Date flightDuration) {
        this.route = route;
        this.rdAirModifier = rdAirModifier;
        this.departureTime = departureTime;
        this.flightDuration = flightDuration;
        this.availableClasses = new ArrayList<>();
    }

    public PriceModifier getRdAirModifier() {
        return rdAirModifier;
    }

    public void setRdAirModifier(PriceModifier rdAirModifier) {
        this.rdAirModifier = rdAirModifier;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Date getFlightDuration() {
        return flightDuration;
    }

    public void setFlightDuration(Date flightDuration) {
        this.flightDuration = flightDuration;
    }

    public int getId() {
        return id;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public List<FlightClass> getAvailableClasses() {
        return availableClasses;
    }
}
