package com.realdolmen.rdAir.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Frederik Van Herbruggen on 4/11/2016.
 */
@Entity
@Table(name = "tblOrder")
public class Order implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Ticket> tickets;

    @Temporal(TemporalType.DATE)
    private Date orderDate;

    private double orderPrice;

    //pending or paid
    private String status; //todo maybe make enum

    //todo needs (average) profit margin for rdair,
    //which also makes it easier to do logging

    protected Order() {
        tickets = new ArrayList<>();
    }

    public Order(List<Ticket> tickets, Date orderDate) {
        this.tickets = tickets;
        if (tickets == null) {
            this.tickets = new ArrayList<>();
        }
        this.orderDate = orderDate;
        this.orderPrice = 0;
        this.status = "pending";
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }
}
