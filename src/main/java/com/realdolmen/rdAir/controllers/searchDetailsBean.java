package com.realdolmen.rdAir.controllers;


import com.realdolmen.rdAir.domain.Customer;
import com.realdolmen.rdAir.domain.Flight;
import com.realdolmen.rdAir.domain.Ticket;
import com.realdolmen.rdAir.repositories.FlightRepository;
import com.realdolmen.rdAir.repositories.SearchRepository;
import com.realdolmen.rdAir.repositories.UserRepository;
import com.realdolmen.rdAir.util.PriceCalculator;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class searchDetailsBean implements Serializable {

    private List<String> flightDetails;


    private int id;


    @Inject
    private UserRepository ur;

    @Inject
    private SearchRepository sr;

    @Inject
    private FlightRepository fr;

    @Inject
    private LoginBean login;

    @PostConstruct
    public void init() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //    public String addTicket() {
//        Order o = login.addToOrder(new Ticket(Flight f));
//        return o;
//    }

//    public double calculateFlightPrice() {
//        PriceCalculator.calculatePrice(new Ticket());
//
//    }

}
