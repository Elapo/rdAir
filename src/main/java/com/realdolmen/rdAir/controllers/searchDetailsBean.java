package com.realdolmen.rdAir.controllers;


import com.realdolmen.rdAir.domain.Customer;
import com.realdolmen.rdAir.domain.Flight;
import com.realdolmen.rdAir.domain.Ticket;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@ViewScoped
public class searchDetailsBean implements Serializable {

    private List<Ticket> tickets;

    @ManagedProperty(value="#{loginBean}")
    private LoginBean login;
    

}
