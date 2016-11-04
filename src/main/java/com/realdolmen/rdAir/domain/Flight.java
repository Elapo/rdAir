package com.realdolmen.rdAir.domain;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

/**
 * Created by Frederik Van Herbruggen on 4/11/2016.
 */
@Entity
public class Flight extends Route{
    @OneToOne
    private PriceModifier rdAirModifier;

    @Temporal(TemporalType.DATE)
    private Date departureTime;

    @Temporal(TemporalType.TIME)
    private Date flightDuration;

    protected Flight() {
        super();
    }

    public Flight(Location departureLocation, Location destination, List<PriceModifier> modifiers, List<PriceModifier> rdModifiers, PriceModifier rdAirModifier, Date departureTime, Date flightDuration) {
        super(departureLocation, destination, modifiers, rdModifiers);
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
}
