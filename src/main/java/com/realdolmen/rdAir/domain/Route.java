package com.realdolmen.rdAir.domain;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Frederik Van Herbruggen on 4/11/2016.
 */
@MappedSuperclass
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    private Location departureLocation;

    @OneToOne
    private Location destination;

    @OneToMany
    private List<PriceModifier> modifiers;

    @OneToMany
    private List<PriceModifier> rdModifiers;

    protected Route() {
    }

    public Route(Location departureLocation, Location destination, List<PriceModifier> modifiers, List<PriceModifier> rdModifiers) {
        this.departureLocation = departureLocation;
        this.destination = destination;
        this.modifiers = modifiers;
        this.rdModifiers = rdModifiers;
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
}

