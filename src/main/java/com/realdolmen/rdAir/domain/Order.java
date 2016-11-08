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

    @OneToMany(fetch = FetchType.LAZY)
    private List<Ticket> tickets;

    @Temporal(TemporalType.DATE)
    private Date orderDate;

    private String status; //todo maybe make enum

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
