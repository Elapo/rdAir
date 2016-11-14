package com.realdolmen.rdAir.repositories;

import com.realdolmen.rdAir.domain.FlightClass;
import com.realdolmen.rdAir.utilities.persistence.JpaPersistenceTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;

/**
 * Created by Frederik on 09/11/2016.
 */
public class TestFlightClassRepository extends JpaPersistenceTest{
    EntityManager em;
    FlightClassRepository fcr;

    @Before
    public void init(){
        em = entityManager();
        fcr = new FlightClassRepository();
        fcr.em = em;
    }

    @Test
    public void testFlightClassRepositorySave(){
        FlightClass fc = new FlightClass("first class", 50, 20, 15, null);
        fcr.save(fc);

        Assert.assertNotNull(em.find(FlightClass.class, fc.getId()));
        Assert.assertEquals(fc, em.find(FlightClass.class, fc.getId()));
    }

    @Test
    public void testFlightClassRepositoryFindById(){
        FlightClass fc = new FlightClass("first class", 50, 20, 15, null);
        em.persist(fc);

        Assert.assertNotNull(fcr.findById(fc.getId()));
        Assert.assertEquals(fcr.findById(fc.getId()), fc);
    }

    @Test
    public void testFlightClassRepositoryFindByIdFail(){
        Assert.assertEquals(0, fcr.findByName("dswfsqfd").size());
    }

    @Test
    public void testFlightClassRepositoryFindByName(){
        FlightClass fc = new FlightClass("first class", 50, 20, 15, null);
        em.persist(fc);

        Assert.assertNotNull(fcr.findByName("first class"));
        Assert.assertNotEquals(0, fcr.findByName("first class").size());
    }

    @Test
    public void testFlightClassRepositoryDelete(){
        FlightClass fc = new FlightClass("first class", 50, 20, 15, null);
        em.persist(fc);
        int id = fc.getId();

        Assert.assertNotNull(em.find(FlightClass.class, id));
        fcr.delete(id);
        Assert.assertNull(em.find(FlightClass.class, id));
    }
    @Test
    public void testFlightClassRepositoryDeleteFail(){
        Assert.assertFalse(fcr.delete(43543534));
    }
}
