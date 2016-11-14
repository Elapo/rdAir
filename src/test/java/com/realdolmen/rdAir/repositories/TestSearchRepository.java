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
    CriteriaSearchRepository csr;

    Flight f;
    FlightClass fc;


    @Before
    public void init(){
        em = entityManager();
        search = new SearchRepository();
        search.em = em;

        csr = new CriteriaSearchRepository();

        Region r = new Region("America");
        Location l1 = new Location("Airport", "APT", r);
        Location l2 = new Location("SecondAirport", "SPT", r);
        Airline a = new Airline("Frederik", "Van Herbruggen", "FCL34", "0474416357", "realdolmenair@gmail.com",
                "rdair", "http://rdair.com", "abc");

        Route route = new Route(l1, l2, null, null, a);
        a.getRoutes().add(route);
        f = new Flight(route, null, new Date(), new Date());
        a.getFlights().add(f);
        fc = new FlightClass("First Class", 50, 30, 30, f);
        f.getAvailableClasses().add(fc);

        em.persist(a);
        em.flush();
    }

    @Test
    public void testSeachWithoutDateParameters(){
        Ticket t = new Ticket(f, fc);
        em.persist(t);

        List<Ticket> results = search.searchFlights(2, "First Class", "rdair", "Airport", "SecondAirport", "", null);
        Assert.assertNotNull(results.get(0));
    }

    @Test
    public void testSearchWithoutDateWithRegion(){
        Ticket t = new Ticket(f, fc);
        em.persist(t);
        System.out.println(t.getFlight().getAvailableFirstClass());
        List<Ticket> results = search.searchFlights(2, "First Class", "rdair", "", "", "America", null);
        Assert.assertNotNull(results.get(0));
    }

    @Test
    public void testOtherSearch(){
        System.out.println(csr.searchFlights(0, "", "rdair", "", "", "", null));
    }
}