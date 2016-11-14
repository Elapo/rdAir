package com.realdolmen.rdAir.controllers;

import com.realdolmen.rdAir.domain.*;
import com.realdolmen.rdAir.repositories.CriteriaSearchRepository;
import com.realdolmen.rdAir.util.PriceCalculator;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Named
@SessionScoped
public class SearchFlightBean implements Serializable {

//    @Inject
//    private CriteriaSearchRepository criteriaSearchRepository;

    @Inject
    private SearchresultsBean searchResultsBean;

    private Integer id;

    @NotNull(message="{Search.error.desiredNrOfSeats.null}")
    @Min(value=1,message="{Search.error.desiredNrOfSeats}")
    @Max(value=853, message="{Search.error.desiredNrOfSeats}")
    private Integer desiredNrOfSeats;

    @NotNull(message="{Search.error.flightClass.null}")
    private String flightClass;
    @NotNull(message="{Search.error.airlineCompany.null}")
    private String preferredAirline;


    private String locationOption;
    private boolean clickedLocationOptionOne=false;
    private boolean clickedLocationOptionTwo=false;
    @NotNull(message="{Search.error.departureLocation.null}")
    private String departureLocation;
    @NotNull(message="{Search.error.destinationLocation.null}")
    private String destinationLocation;
    @NotNull(message="{Search.error.globalRegion.null}")
    private String globalRegion;

    @NotNull(message="{Search.error.dateOfDeparture.null}")
    @Future(message="{Search.error.dateOfDeparture.future}")
    private Date dateOfDeparture;

    @Future(message="{Search.error.dateOfReturn.future}")
    private Date dateOfReturn;

    private String flightWay;
    private boolean clickedReturn=false;

    @PostConstruct
    public void init() {
        flightWay = "One way";
        locationOption = "Departure location - Destination";
    }

    public Integer getDesiredNrOfSeats() {
        return desiredNrOfSeats;
    }

    public void setDesiredNrOfSeats(Integer desiredNrOfSeats) {
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
        System.err.println("LOCATION LISTENER CALLED");
        clickedLocationOptionOne = locationOption.equals("Departure Location - Destination");
        clickedLocationOptionTwo = locationOption.equals("World region");

    }

    public void listener1() {
        System.err.println("DATE LISTENER CALLED");
        clickedReturn = flightWay.equals("Return");
    }

    public boolean getClickedReturn() {
        return clickedReturn;
    }

    public boolean getClickedLocationOptionOne() {
        return clickedLocationOptionOne;
    }

    public boolean getClickedLocationOptionTwo() { return  clickedLocationOptionTwo; }


    public void setClickedLocationOptionOne(boolean clickedLocationOptionOne) {
        this.clickedLocationOptionOne = clickedLocationOptionOne;
    }

    public void setClickedLocationOptionTwo(boolean clickedLocationOptionTwo) {
        this.clickedLocationOptionTwo = clickedLocationOptionTwo;
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

    public String search(){
        //TODO: give search criteria to sql search query
        //SHOULD GIVE search results but returns null..
        //  can't even inject this
//        searchResultsBean.setSearchResults(criteriaSearchRepository.searchFlights(desiredNrOfSeats, flightClass, preferredAirline, departureLocation,
//                destinationLocation, globalRegion, dateOfDeparture));


        System.err.println("Pressed search button");
        return "searchresults";
    }


}
