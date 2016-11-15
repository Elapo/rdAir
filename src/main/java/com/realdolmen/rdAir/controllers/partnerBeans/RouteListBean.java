package com.realdolmen.rdAir.controllers.partnerBeans;

import com.realdolmen.rdAir.controllers.LoginBean;
import com.realdolmen.rdAir.domain.Airline;
import com.realdolmen.rdAir.domain.Location;
import com.realdolmen.rdAir.domain.Route;
import com.realdolmen.rdAir.repositories.LocationRepository;
import com.realdolmen.rdAir.repositories.RouteRepository;
import org.hibernate.Hibernate;
import org.primefaces.event.RowEditEvent;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Frederik on 14/11/2016.
 */
@ManagedBean
@ViewScoped
public class RouteListBean implements Serializable{
    private List<Route> routes;

    @ManagedProperty(value = "#{loginBean}")
    private LoginBean session;

    @Inject
    private RouteRepository rr;

    @Inject
    private LocationRepository lr;

    private Route selected;

    private Location dep, dest;

    private List<Location> locations;

    @PostConstruct
    public void init(){
        routes = new ArrayList<>();
        locations = new ArrayList<>();
        routes = rr.getAllForAirline((Airline) session.getUser());
        locations = lr.getAllLocations();
    }

    public void editRoute(RowEditEvent e){
        
        routes = rr.getAllForAirline((Airline) session.getUser());
    }

    public void addRoute(){
        rr.save(new Route(dep, dest, null, null, (Airline) session.getUser()));
        routes = rr.getAllForAirline((Airline) session.getUser());
    }

    public void deleteRoute(){
        rr.delete(selected.getId());
        routes = rr.getAllForAirline((Airline) session.getUser());
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public Route getSelected() {
        return selected;
    }

    public void setSelected(Route selected) {
        this.selected = selected;
    }

}
