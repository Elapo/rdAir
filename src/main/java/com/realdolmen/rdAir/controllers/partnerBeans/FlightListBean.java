package com.realdolmen.rdAir.controllers.partnerBeans;

import com.realdolmen.rdAir.controllers.LoginBean;
import com.realdolmen.rdAir.domain.Location;
import com.realdolmen.rdAir.repositories.FlightRepository;
import com.realdolmen.rdAir.repositories.LocationRepository;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class FlightListBean {

    @Inject
    LoginBean login;

    @Inject
    FlightRepository fr;

    @Inject
    LocationRepository lr;

    List<Location> locations;

    @PostConstruct
    public void init(){
        locations = lr.getAllLocations();
        if (locations == null) {
            locations = new ArrayList<>();
        }
    }

    public void addFlight(){

    }

    public void editFlight(){

    }
}
