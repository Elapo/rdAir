package com.realdolmen.rdAir.controllers;


import com.realdolmen.rdAir.domain.Customer;
import com.realdolmen.rdAir.domain.Flight;
import com.realdolmen.rdAir.domain.Ticket;
import com.realdolmen.rdAir.util.PriceCalculator;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class searchDetailsBean implements Serializable {

    private List<Ticket> tickets;

    private int id;

    @ManagedProperty(value="#{loginBean}")
    private LoginBean login;

    @PostConstruct
    public void init() {
        tickets = new ArrayList<>();
    }

//    public String addTicket() {
//        login.addToOrder(new Ticket(Flight f));
//        return ;
//    }

//    public double calculateFlightPrice() {
//        PriceCalculator.calculatePrice(new Ticket());
//
//    }

}
