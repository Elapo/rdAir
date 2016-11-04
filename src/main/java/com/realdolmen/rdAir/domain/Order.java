package com.realdolmen.rdAir.domain;

import javax.persistence.*;
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
}
