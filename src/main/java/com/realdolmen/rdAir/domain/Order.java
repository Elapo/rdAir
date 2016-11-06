package com.realdolmen.rdAir.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Frederik Van Herbruggen on 4/11/2016.
 */
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany
    private List<Ticket> tickets;

    @Temporal(TemporalType.DATE)
    private Date orderDate;

    protected Order() {
        tickets = new ArrayList<>();
    }

    public Order(List<Ticket> tickets, Date orderDate) {
        this.tickets = new ArrayList<>();
        this.tickets = tickets;
        this.orderDate = orderDate;
    }

    public int getId() {
        return id;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}
