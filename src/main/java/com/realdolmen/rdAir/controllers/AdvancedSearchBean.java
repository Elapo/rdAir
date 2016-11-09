package com.realdolmen.rdAir.controllers;

import com.realdolmen.rdAir.domain.*;
import com.realdolmen.rdAir.repositories.FlightClassRepository;
import com.realdolmen.rdAir.repositories.LocationRepository;
import com.realdolmen.rdAir.repositories.RouteRepository;
import com.realdolmen.rdAir.repositories.UserRepository;
import org.hibernate.Criteria;
import org.hibernate.Session;

import javax.inject.Inject;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

public class AdvancedSearchBean {
    @PersistenceContext
    private Session s;

    @Inject
    RouteRepository rr;

    @Inject
    FlightClassRepository fcrp;

    @Inject
    UserRepository ur;

    @Inject
    LocationRepository lr;

    private int seats;

    private String flightClass;

    private String airline;

    private String departure, destination;

    private String Region;

    private Date departureDate, returnDate;

    private Criteria criteria;

    public void doSearch(){
        List<FlightClass> fc;
        Airline a;
        List<Location> dep, dest;
        if(flightClass != null && !flightClass.trim().isEmpty()){
            fc = fcrp.findByName(flightClass);
        }
        if(airline != null && !airline.trim().isEmpty()){
            a = ur.findByName(airline, Airline.class);
        }
        if(departure != null && !departure.trim().isEmpty()){
            dep = lr.findByName(departure);
        }
        if(destination != null && !destination.trim().isEmpty()){
            dest = lr.findByName(destination);
        }
        criteria = s.createCriteria(Flight.class);
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public String getFlightClass() {
        return flightClass;
    }

    public void setFlightClass(String flightClass) {
        this.flightClass = flightClass;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getRegion() {
        return Region;
    }

    public void setRegion(String region) {
        Region = region;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Criteria getCriteria() {
        return criteria;
    }

    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }
}
