package com.realdolmen.rdAir.domain;

import javax.persistence.*;

/**
 * Created by Frederik Van Herbruggen on 4/11/2016.
 */
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Flight flight;

    @ManyToOne
    private FlightClass flightClass;

    protected Ticket() {
    }

    public Ticket(Flight flight, FlightClass flightClass) {
        this.flight = flight;
        this.flightClass = flightClass;
    }

    public int getId() {
        return id;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public FlightClass getFlightClass() {
        return flightClass;
    }

    public void setFlightClass(FlightClass flightClass) {
        this.flightClass = flightClass;
    }
}
