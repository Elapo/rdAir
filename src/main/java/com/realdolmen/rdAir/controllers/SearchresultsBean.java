package com.realdolmen.rdAir.controllers;

import com.realdolmen.rdAir.domain.*;
import com.realdolmen.rdAir.util.PriceCalculator;
import org.hibernate.Hibernate;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@ManagedBean
@ViewScoped
public class SearchresultsBean implements Serializable {


    @Inject
    SearchFlightBean sfb;

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
        flights = sfb.getResults();
        System.out.println(" flights = " + flights);
        getdata();
    }

    @Transactional
    private void getdata(){
        for (Flight flight : flights) {
            Hibernate.initialize(flight.getRoute().getAirline());
        }
    }

    public double calculateDiscount(Flight f, String fClass) {
        FlightClass toCalc = null;
        for(FlightClass fc :f.getAvailableClasses()){
            if (fc.getName().equals(fClass)){
                toCalc = fc;
                break;
            }
        }
        if (toCalc != null) {
            return PriceCalculator.getDiscountAmount(toCalc);
        }
        return 0;
    }

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
