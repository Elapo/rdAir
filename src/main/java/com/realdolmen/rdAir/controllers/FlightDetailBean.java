package com.realdolmen.rdAir.controllers;


import com.realdolmen.rdAir.domain.Flight;
import com.realdolmen.rdAir.domain.FlightClass;
import com.realdolmen.rdAir.domain.Ticket;
import com.realdolmen.rdAir.repositories.FlightClassRepository;
import com.realdolmen.rdAir.repositories.FlightRepository;
import com.realdolmen.rdAir.util.PriceCalculator;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.transaction.*;

@ManagedBean
@ViewScoped
public class FlightDetailBean {

    private int id;

    private String fClass;

    @Resource
    UserTransaction ut;

    @Inject
    Flight f;

    private FlightClass selectedClass;

    @Inject
    FlightRepository fr;

    @Inject
    FlightClassRepository fcr;

    @ManagedProperty(value = "#{loginBean}")
    LoginBean session;

    @PostConstruct
    public void init(){

    }

    public void setParams(){
        if (id != 0) {
            f = fr.findById(id);
            for (FlightClass flightClass : f.getAvailableClasses()) {
                if (flightClass.getName().equals(fClass)){
                    selectedClass = flightClass;
                    break;
                }
            }
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

    public String bookFlight() throws HeuristicRollbackException, RollbackException, HeuristicMixedException, SystemException, NotSupportedException {
        session.addToOrder(f, selectedClass, calculateFlightPrice(f, fClass));
        selectedClass.setAvailableSeatCount(selectedClass.getAvailableSeatCount()-1);
        ut.begin();
        fcr.save(selectedClass);
        ut.commit();
        return "pretty:view-booking";
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

    public LoginBean getSession() {
        return session;
    }

    public void setSession(LoginBean session) {
        this.session = session;
    }

    public FlightClass getSelectedClass() {
        return selectedClass;
    }

    public void setSelectedClass(FlightClass selectedClass) {
        this.selectedClass = selectedClass;
    }
}
