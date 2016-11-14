package com.realdolmen.rdAir.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Frederik Van Herbruggen on 4/11/2016.
 */
@Entity
public class FlightClass implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private double price;

    private int seatCount;

    private int availableSeatCount;

    private int version;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Flight flight;

    protected FlightClass() {

    }

    public FlightClass(String name, double price, int seatCount, int availableSeatCount, Flight flight) {
        this.name = name;
        this.price = price;
        this.seatCount = seatCount;
        this.availableSeatCount = availableSeatCount;
        this.flight = flight;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(int seatCount) {
        this.seatCount = seatCount;
    }

    public int getAvailableSeatCount() {
        return availableSeatCount;
    }

    public void setAvailableSeatCount(int availableSeatCount) {
        this.availableSeatCount = availableSeatCount;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    @Version
    @Column(name = "lockVersion")
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
