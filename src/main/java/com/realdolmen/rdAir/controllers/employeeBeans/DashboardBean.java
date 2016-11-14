package com.realdolmen.rdAir.controllers.employeeBeans;

import com.realdolmen.rdAir.repositories.OrderRepository;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.io.Serializable;

/**
 * Created by Frederik on 14/11/2016.
 */
@ViewScoped
@ManagedBean
public class DashboardBean implements Serializable{

    private int acceptedOrders;
    private int pendingOrders;

    @Inject
    private OrderRepository or;

    @PostConstruct
    public void init(){
        acceptedOrders = or.getPaidOrderCount();
        pendingOrders = or.getPendingOrderCount();
    }

    public int getAcceptedOrders() {
        return acceptedOrders;
    }

    public void setAcceptedOrders(int acceptedOrders) {
        this.acceptedOrders = acceptedOrders;
    }

    public int getPendingOrders() {
        return pendingOrders;
    }

    public void setPendingOrders(int pendingOrders) {
        this.pendingOrders = pendingOrders;
    }
}
