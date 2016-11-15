package com.realdolmen.rdAir.domain;

import org.hibernate.Hibernate;

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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Route route;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private PriceModifier rdAirModifier;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PriceModifier> priceModifiers;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PriceModifier> rdAirModifiers;

    @Temporal(TemporalType.DATE)
    private Date departureTime;

    @Temporal(TemporalType.TIME)
    private Date flightDuration;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL)
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
        this.priceModifiers = new ArrayList<>();
        this.rdAirModifiers = new ArrayList<>();
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

    public List<PriceModifier> getPriceModifiers() {
        return priceModifiers;
    }

    public List<PriceModifier> getRdAirModifiers() {
        return rdAirModifiers;
    }

    public int getAvailableFirstClass(){
        Hibernate.initialize(availableClasses);
        for(FlightClass fc: availableClasses){
            if(fc.getName().equals("First Class")){
                return fc.getAvailableSeatCount();
            }
        }
        return 0;
    }
    public int getAvailableBusinessClass(){
        Hibernate.initialize(availableClasses);
        for(FlightClass fc: availableClasses){
            if(fc.getName().equals("Business Class")){
                return fc.getAvailableSeatCount();
            }
        }
        return 0;
    }
    public int getAvailableEconomyClass(){
        Hibernate.initialize(availableClasses);
        for(FlightClass fc: availableClasses){
            if(fc.getName().equals("Economy Class")){
                return fc.getAvailableSeatCount();
            }
        }
        return 0;
    }
}
