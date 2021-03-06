package com.realdolmen.rdAir.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by Frederik Van Herbruggen on 4/11/2016.
 */
@Entity
public class Customer extends User implements Serializable{

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Order> orders;

    protected Customer() {
        super();
        orders = new ArrayList<>();
    }

    public Customer(String firstName, String lastName, String address, String telephone, String email,String passwordHash, List<Order> orders) {
        super(firstName, lastName, address, telephone, email, passwordHash);
        if(orders != null) this.orders = orders;
        else orders = new ArrayList<>(); //fixme: do this to other plqces, or remove lists from ctors entirely
    }

    public List<Order> getOrders() {
        return orders;
    }
}
