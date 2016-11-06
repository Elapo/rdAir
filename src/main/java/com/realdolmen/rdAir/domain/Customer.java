package com.realdolmen.rdAir.domain;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by Frederik Van Herbruggen on 4/11/2016.
 */
@Entity
public class Customer extends User {

    @OneToMany
    private List<Order> orders;

    protected Customer() {
        super();
        orders = new ArrayList<>();
    }

    public Customer(String firstName, String lastName, String address, String telephone, String email, List<Order> orders) {
        super(firstName, lastName, address, telephone, email);
        orders = new ArrayList<>();
        this.orders = orders;
    }

    public List<Order> getOrders() {
        return orders;
    }
}
