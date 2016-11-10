package com.realdolmen.rdAir.repositories;

import com.realdolmen.rdAir.domain.Flight;
import com.realdolmen.rdAir.utilities.persistence.JpaPersistenceTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.util.Date;

public class TestFlightRepository extends JpaPersistenceTest {

    EntityManager em;

    FlightRepository fr;

    @Before
    public void init(){
        fr = new FlightRepository();
        em = entityManager();
        fr.em = em;
    }

    @Test
    public void testFlightRepositorySave(){
        Flight f = new Flight(null, null, new Date(), new Date());
        fr.save(f);

        Assert.assertNotNull(em.find(Flight.class, f.getId()));
        Assert.assertEquals(em.find(Flight.class, f.getId()), f);
    }

    @Test
    public void testFlightRepositoryGetByPage(){
        Flight f1 = new Flight(null, null, new Date(), new Date());
        Flight f2 = new Flight(null, null, new Date(), new Date());

        em.persist(f1);
        em.persist(f2);

        Assert.assertNotNull(fr.getFlightsPerPage(1, 10));
        Assert.assertNotEquals(0, fr.getFlightsPerPage(1,10).size());
    }

    @Test
    public void testFlightRepositoryGetByPageFail(){
        Flight f1 = new Flight(null, null, new Date(), new Date());
        Flight f2 = new Flight(null, null, new Date(), new Date());

        em.persist(f1);
        em.persist(f2);

        Assert.assertEquals(0, fr.getFlightsPerPage(2, 10).size());
    }

    @Test
    public void testFlightRepositoryDelete(){
        Flight f = new Flight(null, null, new Date(), new Date());
        em.persist(f);
        int id = f.getId();

        Assert.assertNotNull(em.find(Flight.class, id));
        fr.delete(id);
        Assert.assertNull(em.find(Flight.class, id));
    }

    @Test
    public void testFlightRepositoryDeleteFail(){
        Assert.assertFalse(fr.delete(213412345));
    }
}
