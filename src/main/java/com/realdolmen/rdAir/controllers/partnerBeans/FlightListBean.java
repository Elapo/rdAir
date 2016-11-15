package com.realdolmen.rdAir.controllers.partnerBeans;

import com.realdolmen.rdAir.controllers.LoginBean;
import com.realdolmen.rdAir.domain.Airline;
import com.realdolmen.rdAir.domain.Flight;
import com.realdolmen.rdAir.domain.Location;
import com.realdolmen.rdAir.domain.Route;
import com.realdolmen.rdAir.repositories.FlightRepository;
import com.realdolmen.rdAir.repositories.LocationRepository;
import com.realdolmen.rdAir.repositories.RouteRepository;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ManagedBean
@ViewScoped
public class FlightListBean {

    @Inject
    LoginBean login;

    @Inject
    FlightRepository fr;

    @Inject
    RouteRepository rr;

    List<Route> routes;

    List<Flight> flights;

    private Route route;

    private Date departure, duration;

    @PostConstruct
    public void init(){
        if(login.isLoggedIn()){
            routes = rr.getAllForAirline((Airline) login.getUser());
            if (routes == null) {
                routes = new ArrayList<>();
            }
            flights =((Airline) login.getUser()).getFlights();
            if (flights == null) {
                flights=new ArrayList<>();
            }
        }
        else{//for debug
            flights = new ArrayList<>();
            routes = new ArrayList<>();
        }
    }

    public void addFlight(){

    }

    public void editFlight(){

    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Date getDeparture() {
        return departure;
    }

    public void setDeparture(Date departure) {
        this.departure = departure;
    }

    public Date getDuration() {
        return duration;
    }

    public void setDuration(Date duration) {
        this.duration = duration;
    }
}
