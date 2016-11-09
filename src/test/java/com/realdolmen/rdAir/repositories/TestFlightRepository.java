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
    public void testFlightRepositoryDelete(){
        Flight f = new Flight(null, null, new Date(), new Date());
        em.persist(f);
        int id = f.getId();

        Assert.assertNotNull(em.find(Flight.class, id));
        fr.delete(id);
        Assert.assertNull(em.find(Flight.class, id));
    }
}
