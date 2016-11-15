package com.realdolmen.rdAir.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Frederik Van Herbruggen on 4/11/2016.
 */
@Entity
public class Route implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Location departureLocation;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Location destination;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<PriceModifier> modifiers;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<PriceModifier> rdModifiers;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Airline airline;

    protected Route() {
        modifiers = new ArrayList<>();
        rdModifiers = new ArrayList<>();
    }

    public Route(Location departureLocation, Location destination, List<PriceModifier> modifiers, List<PriceModifier> rdModifiers, Airline airline) {
        this.departureLocation = departureLocation;
        this.destination = destination;
        this.airline = airline;
        this.modifiers = new ArrayList<>();
        this.rdModifiers = new ArrayList<>();
        this.modifiers = modifiers;
        this.rdModifiers = rdModifiers;
    }

    @Override
    public String toString() {
        return departureLocation.getAirportName() +" to "+destination.getAirportName();
    }

    public int getId() {
        return id;
    }

    public Location getDepartureLocation() {
        return departureLocation;
    }

    public void setDepartureLocation(Location departureLocation) {
        this.departureLocation = departureLocation;
    }

    public Location getDestination() {
        return destination;
    }

    public void setDestination(Location destination) {
        this.destination = destination;
    }

    public List<PriceModifier> getModifiers() {
        return modifiers;
    }

    public List<PriceModifier> getRdModifiers() {
        return rdModifiers;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }
}

