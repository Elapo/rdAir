package com.realdolmen.rdAir.repositories;

import com.realdolmen.rdAir.controllers.CalendarViewBean;
import com.realdolmen.rdAir.domain.*;
import com.realdolmen.rdAir.utilities.persistence.JpaPersistenceTest;
import org.junit.Before;
import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by HBSBB70 on 14/11/2016.
 */
public class LoadFlights extends JpaPersistenceTest {

    EntityManager em;

    UserRepository userRepository;

    @Before
    public void init()
    {
        em = entityManager();
        userRepository = new UserRepository();
        userRepository.em = em;
    }

    @Test
    public void loadData(){
        //String firstName, String lastName, String address, String telephone, String email,
        // String passwordHash, List<Order> orders
        String hashedPass = BCrypt.hashpw("12345678", BCrypt.gensalt());
        Customer c = new Customer("Customer", "Lastname", "address", "015123456","customer@gmail.com",
                hashedPass, new ArrayList<Order>());
        userRepository.save(c);
        assertNotNull(c.getId());
        assertNotEquals("12345678", c.getPasswordHash());


        ArrayList<Region> regions = new ArrayList<Region>();
        regions.add(new Region("Africa"));
        regions.add(new Region("Asia"));
        regions.add(new Region("North America"));
        regions.add(new Region("South America"));
        regions.add(new Region("Middle East"));
        regions.add(new Region("Oceania"));
        regions.add(new Region("Europe"));
        for(Region r : regions){
            em.persist(r);
            assertNotNull(r.getId());
        }
        ArrayList<Location> locations = new ArrayList<>();
        locations.add(new Location("Brussels", "BRU", regions.get(6)));
        locations.add(new Location("Charleroi", "CRL", regions.get(6)));
        locations.add(new Location("New York", "JFK", regions.get(2)));
        for(Location l : locations){
            em.persist(l);
            assertNotNull(l.getId());
        }

        // String website, String passwordHash

        Airline a = new Airline("Ryan","Air","Ireland","42","ryanair@air.com",
                "Ryanair","https://www.ryanair.com",hashedPass);
        em.persist(a);
        assertNotNull(a.getId());

        //Location(String airportName, String airportCode, Region region)
        Route route = new Route(locations.get(0),locations.get(2),new ArrayList<PriceModifier>(), new ArrayList<PriceModifier>(),a);
        // Route(Location departureLocation, Location destination, List<PriceModifier> modifiers,
        // List<PriceModifier> rdModifiers, Airline airline) {
        for(int i = 0; i < 5; i++){
            // PRICE MODIFIER: String name, Date startDate, Date endDate, Date startTime, Date endTime, boolean isPercent,
            // boolean isVolumeDiscount, double amount)
            PriceModifier pm = new PriceModifier("name",new Date(),new Date(),new Date(),new Date(), true, false, 0.1);
            //public Flight(Route route, PriceModifier rdAirModifier, Date departureTime, Date flightDuration)
            Date today = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(today);
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            Date tomorrow = calendar.getTime();
            Flight f = new Flight(route, pm, tomorrow, new Date());

            FlightClass fc = new FlightClass("Economy Class", 10.0, 10, 10, f);
            // FlightClass(String name, double price, int seatCount, int availableSeatCount, Flight flight)
            f.addClass(fc);
            fc = new FlightClass("Business Class", 15.0, 10, 10, f);
            f.addClass(fc);
            fc = new FlightClass("First Class", 20.0, 10, 10, f);
            f.addClass(fc);

            em.persist(f);
            assertNotNull(f.getId());
        }
    }
}
