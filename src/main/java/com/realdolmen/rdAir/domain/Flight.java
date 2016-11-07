package com.realdolmen.rdAir.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Frederik Van Herbruggen on 4/11/2016.
 */
@Entity
public class Flight implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Route route;

    @OneToOne(fetch = FetchType.LAZY)
    private PriceModifier rdAirModifier;

    @Temporal(TemporalType.DATE)
    private Date departureTime;

    @Temporal(TemporalType.TIME)
    private Date flightDuration;

    protected Flight() {
        super();
    }

    public Flight(Route route, PriceModifier rdAirModifier, Date departureTime, Date flightDuration) {
        this.route = route;
        this.rdAirModifier = rdAirModifier;
        this.departureTime = departureTime;
        this.flightDuration = flightDuration;
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
}
