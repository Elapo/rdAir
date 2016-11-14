package com.realdolmen.rdAir.controllers;

import com.realdolmen.rdAir.domain.Customer;
import com.realdolmen.rdAir.domain.Order;
import org.hibernate.Hibernate;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class OrderListBean implements Serializable {

    private List<Order> orders;

    @ManagedProperty(value="#{loginBean}")
    private LoginBean login;

    @PostConstruct
    public void init() {
        orders = new ArrayList<>();
        orders = ((Customer) login.getUser()).getOrders();
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public LoginBean getLogin() {
        return login;
    }

    public void setLogin(LoginBean login) {
        this.login = login;
    }
}
