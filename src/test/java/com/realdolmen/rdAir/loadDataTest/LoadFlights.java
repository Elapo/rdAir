package com.realdolmen.rdAir.loadDataTest;

import com.realdolmen.rdAir.domain.*;
import com.realdolmen.rdAir.utilities.persistence.JpaPersistenceTest;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by HBSBB70 on 14/11/2016.
 */
public class LoadFlights extends JpaPersistenceTest {

    EntityManager em;

    @Before
    public void init(){
        em = entityManager();
    }

    @Test
    public void loadData(){
        Region r = new Region("Asia");
        em.persist(r);
        Location l = new Location("x", "x", r);
        // (String firstName, String lastName, String address, String telephone, String email, String airlineName,
        // String website, String passwordHash)
        Airline a = new Airline("f","l","a","42","a@a.com","AZ Air","https://www.google.be","passwordhash");
        //Location(String airportName, String airportCode, Region region)
        Route route = new Route(l,l,new ArrayList<PriceModifier>(), new ArrayList<PriceModifier>(),a);
        // Route(Location departureLocation, Location destination, List<PriceModifier> modifiers,
        // List<PriceModifier> rdModifiers, Airline airline) {
        for(int i = 0; i < 5; i++){
            PriceModifier pm = new PriceModifier("name",new Date(),new Date(),new Date(),new Date(), true, false,0.01);
            Flight f = new Flight(route, pm, new Date(), new Date());
            //public Flight(Route route, PriceModifier rdAirModifier, Date departureTime, Date flightDuration)


            //String name, Date startDate, Date endDate, Date startTime, Date endTime, boolean isPercent, boolean isVolumeDiscount,
            // double amount) {
            FlightClass fc = new FlightClass("Economy Class", 10.0, 10, 10, f);
            // FlightClass(String name, double price, int seatCount, int availableSeatCount, Flight flight)
            f.addClass(fc);

            fc = new FlightClass("Business Class", 10.0, 10, 10, f);
            f.addClass(fc);
            fc = new FlightClass("First Class", 10.0, 10, 10, f);
            f.addClass(fc);

            em.persist(f);
        }
    }
}
