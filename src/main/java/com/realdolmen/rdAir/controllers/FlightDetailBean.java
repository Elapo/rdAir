package com.realdolmen.rdAir.controllers;


import com.realdolmen.rdAir.domain.Flight;
import com.realdolmen.rdAir.domain.FlightClass;
import com.realdolmen.rdAir.domain.Ticket;
import com.realdolmen.rdAir.repositories.FlightRepository;
import com.realdolmen.rdAir.util.PriceCalculator;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

@ManagedBean
@ViewScoped
public class FlightDetailBean {

    private int id;

    private String fClass;

    @Inject
    Flight f;

    @Inject
    FlightRepository fr;

    @Inject
    LoginBean session;

    @PostConstruct
    public void init(){
        if (id != 0) {
            f = fr.findById(id);
        }
    }

    public double calculateFlightPrice(Flight f, String fClass) {
        FlightClass toCalc = null;
        if(f.getAvailableClasses() == null) return 0;
        for(FlightClass fc :f.getAvailableClasses()){
            if (fc.getName().equals(fClass)){
                toCalc = fc;
                break;
            }
        }
        if (toCalc != null) {
            return PriceCalculator.calculatePrice(toCalc);
        }
        return 0;
    }

    public double calculateDiscount(Flight f, String fClass) {
        FlightClass toCalc = null;
        if(f.getAvailableClasses() == null) return 0;
        for (FlightClass fc : f.getAvailableClasses()) {
            if (fc.getName().equals(fClass)) {
                toCalc = fc;
                break;
            }
        }
        if (toCalc != null) {
            return PriceCalculator.getDiscountAmount(toCalc);
        }
        return 0;
    }

    public String bookFlight(){
        FlightClass fc = null;
        for (FlightClass flightClass : f.getAvailableClasses()) {
            if(flightClass.getName().equals(fClass)){
                fc = flightClass;
                break;
            }
        }
        if (fc != null) {
            session.getBooking().getTickets().add(new Ticket(f, fc));
            return "pretty:view-booking";
        }
        return "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Flight getF() {
        return f;
    }

    public void setF(Flight f) {
        this.f = f;
    }

    public String getfClass() {
        return fClass;
    }

    public void setfClass(String fClass) {
        this.fClass = fClass;
    }
}
