package com.realdolmen.rdAir.repositories;

import com.realdolmen.rdAir.domain.*;
import com.realdolmen.rdAir.utilities.persistence.JpaPersistenceTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

public class TestSearchRepository extends JpaPersistenceTest {

    EntityManager em;
    SearchRepository search;

    Flight f;
    FlightClass fc;


    @Before
    public void init(){
        em = entityManager();
        search = new SearchRepository();
        search.em = em;
        Region r = new Region("America");
        Location l1 = new Location("Airport", "APT", r);
        Location l2 = new Location("SecondAirport", "SPT", r);
        Airline a = new Airline("Frederik", "Van Herbruggen", "FCL34", "0474416357", "realdolmenair@gmail.com",
                "rdair", "http://rdair.com", "abc");

        Route route = new Route(l1, l2, null, null, a);
        a.getRoutes().add(route);
        f = new Flight(route, null, new Date(), new Date());
        a.getFlights().add(f);
        fc = new FlightClass("second", 50, 30, 30, f);

        em.persist(a);
        em.flush();
    }

    @Test
    public void testSeachWithoutDateParameters(){
        Ticket t = new Ticket(f, fc);
        em.persist(t);

        List<Ticket> results = search.searchFlights(2, "second", "rdair", "", "", "", null);
        Assert.assertNotNull(results);
        System.out.println(results.get(0).getFlightClass().getName());
    }
}
