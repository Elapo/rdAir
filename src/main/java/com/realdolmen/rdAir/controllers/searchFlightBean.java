package com.realdolmen.rdAir.controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Hade on 6/11/2016.
 */
@ManagedBean
@RequestScoped
public class searchFlightBean implements Serializable {

    @NotNull //(message="{error.desiredNrOfSeats}")
    @Size(min=1,max=189)
    private int desiredNrOfSeats;

    @NotNull //(message="{error.flightClass}")
    private String flightClass;
    @NotNull //(message="{error.preferredAirline}")
    private String preferredAirline;


    private String locationOption;
    private boolean clickedLocationOptionOne=false;
    @NotNull
    private String departureLocation;
    @NotNull
    private String destinationLocation;
    @NotNull
    private String globalRegion;

    @NotNull //(message="{error.dateOfDeparture}")
    @Future
    private Date dateOfDeparture;

    @NotNull
    private String flightWay;
    private boolean clickedReturn=false;
    @NotNull
    @Future
    private Date dateOfReturn;


    public int getDesiredNrOfSeats() {
        return desiredNrOfSeats;
    }

    public void setDesiredNrOfSeats(int desiredNrOfSeats) {
        this.desiredNrOfSeats = desiredNrOfSeats;
    }

    public String getFlightClass() {
        return flightClass;
    }

    public void setFlightClass(String flightClass) {
        this.flightClass = flightClass;
    }

    public String getPreferredAirline() {
        return preferredAirline;
    }

    public void setPreferredAirline(String preferredAirline) {
        this.preferredAirline = preferredAirline;
    }

    public String getLocationOption() {
        return locationOption;
    }

    public void setLocationOption(String locationOption) {
        this.locationOption = locationOption;
    }

    public void listener() {
        clickedLocationOptionOne = locationOption.contains("Departure Location - Destination Location");
    }

    public void listener1() {
        clickedReturn = flightWay.contains("Return");
    }

    public boolean getClickedLocationOptionOne() {
        return clickedLocationOptionOne;
    }


    public void setClickedLocationOptionOne(boolean clickedLocationOptionOne) {
        this.clickedLocationOptionOne = clickedLocationOptionOne;
    }

    public boolean getClickedReturn() {
        return clickedReturn;
    }

    public void setClickedReturn(boolean clickedReturn) {
        this.clickedReturn = clickedReturn;
    }

    public String getDepartureLocation() {
        return departureLocation;
    }

    public void setDepartureLocation(String departureLocation) {
        this.departureLocation = departureLocation;
    }

    public String getDestinationLocation() {
        return destinationLocation;
    }

    public void setDestinationLocation(String destinationLocation) {
        this.destinationLocation = destinationLocation;
    }

    public String getGlobalRegion() {
        return globalRegion;
    }

    public void setGlobalRegion(String globalRegion) {
        this.globalRegion = globalRegion;
    }

    public Date getDateOfDeparture() {
        return dateOfDeparture;
    }

    public void setDateOfDeparture(Date dateOfDeparture) {
        this.dateOfDeparture = dateOfDeparture;
    }

    public String getFlightWay() {
        return flightWay;
    }

    public void setFlightWay(String flightWay) {
        this.flightWay = flightWay;
    }

    public Date getDateOfReturn() {
        return dateOfReturn;
    }

    public void setDateOfReturn(Date dateOfReturn) {
        this.dateOfReturn = dateOfReturn;
    }
}
