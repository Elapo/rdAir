package com.realdolmen.rdAir.controllers;

import com.realdolmen.rdAir.domain.*;
import com.realdolmen.rdAir.util.PriceCalculator;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@ManagedBean
@ViewScoped
public class SearchresultsBean implements Serializable {


    private List<Flight> flights;


    public List<Flight> getFlights() {
        return flights;
    }

    public void setSearchResults(List<Flight> flights){
        this.flights = flights;
    }

    @PostConstruct
    public void init() {
        flights = new ArrayList<>();
        flights = getSearchResults();
    }

//    public double calculateFlightPrice() {
//        PriceCalculator.calculatePrice(new Ticket());
//
//    }

//    public int calculateDiscount() {
//        PriceModifier pm = new PriceModifier();
//    }

    public List<Flight> getSearchResults(){
        List<Flight> results = new ArrayList<>();
        //TODO: get flight search results from backend
        for(int i = 0; i < 5; i++){
            Location l1 = new Location("airportName"+i, "airportCode"+i, new Region("region"+i));
            Route r1 = new Route(l1, l1, new ArrayList<PriceModifier>(),new ArrayList<PriceModifier>(), null);

            //String name, Date startDate, Date endDate, Date startTime, Date endTime, boolean isPercent, double amount
            PriceModifier pm = new PriceModifier("name", new Date(), new Date(), new Date(), new Date(), true, true, 50);
            //Route route, PriceModifier rdAirModifier, Date departureTime, Date flightDuration
            Flight f = new Flight(r1, pm, new Date(), new Date());
            results.add(f);
        }

        return results;
    }
}
