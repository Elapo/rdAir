package com.realdolmen.rdAir.domain;

import com.realdolmen.rdAir.utilities.persistence.JpaPersistenceTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import javax.persistence.EntityManager;
import java.util.Date;

public class TestDomainPersistence extends JpaPersistenceTest {

    EntityManager em;

    @Before
    public void init(){
        em = entityManager();
    }

    //todo test cascades
    @Test
    public void testAirlinePersistence(){
        Airline a = new Airline("Frederik", "Van Herbruggen", "FCL 34", "0474416357", "email@email.com", "freAir", "http://freAir.com", "abc");
        em.persist(a);

        Airline aPersisted = em.find(Airline.class, a.getId());
        Assert.assertNotNull(aPersisted);
        Assert.assertEquals(aPersisted.getFirstName(), "Frederik");
    }

    @Test
    public void testCustomerPersistence(){
        Customer c = new Customer("Frederik", "Van Herbruggen", "FCL 34", "0474416357", "email@email.com","abc", null);
        em.persist(c);

        Customer cPersisted = em.find(Customer.class, c.getId());
        Assert.assertNotNull(cPersisted);
        Assert.assertEquals(cPersisted.getFirstName(), "Frederik");
    }

    @Test
    public void testFlightPersistence() throws Exception {
        Flight f = new Flight(null, null, null, null);
        em.persist(f);

        Flight fPersisted = em.find(Flight.class, f.getId());
        Assert.assertNotNull(fPersisted);
    }

    @Test
    public void testFlightClassPersistence(){
        FlightClass fc = new FlightClass("First class", 2.50, 30, 30, null);
        em.persist(fc);

        FlightClass fcPersisted = em.find(FlightClass.class, fc.getId());
        Assert.assertNotNull(fcPersisted);
        Assert.assertEquals(fcPersisted.getName(), "First class");
    }

    @Test
    public void testLocationPersistence(){
        Location l = new Location("Airport", "arpt5503", null);

        em.persist(l);
        Location lPersisted = em.find(Location.class, l.getId());

        Assert.assertNotNull(lPersisted);
        Assert.assertEquals(lPersisted.getAirportCode(), "arpt5503");
    }

    @Test
    public void testOrderPersistence(){
        Date d = new Date();
        Order o = new Order(null,d);

        em.persist(o);
        Order oPersisted = em.find(Order.class, o.getId());

        Assert.assertNotNull(oPersisted);
        Assert.assertEquals(oPersisted.getOrderDate(), d);
    }

    @Test
    public void testPriceModifierPersistence(){
        PriceModifier pm = new PriceModifier("Rich tax", new Date(), new Date(), new Date(), new Date(), true, false, 20000000000d);

        em.persist(pm);
        PriceModifier pmPersisted = em.find(PriceModifier.class, pm.getId());

        Assert.assertNotNull(pmPersisted);
        Assert.assertEquals(pmPersisted.getName(), "Rich tax");
    }

    @Test
    public void testRDEmployeePersistence(){
        RDEmployee rde = new RDEmployee("Frederik", "Van Herbruggen", "FCL 34", "0474416357", "email@email.com", "abc", null);

        em.persist(rde);
        RDEmployee rdePersisted = em.find(RDEmployee.class, rde.getId());

        Assert.assertNotNull(rdePersisted);
        Assert.assertEquals(rdePersisted.getFirstName(), "Frederik");
    }

    @Test
    public void testRegionPersistence(){
        Region r = new Region("America");

        em.persist(r);
        Region rPersisted = em.find(Region.class, r.getId());

        Assert.assertNotNull(rPersisted);
        Assert.assertEquals(rPersisted.getName(), "America");
    }

    @Test
    public void testRoutePersistence(){
        Route r = new Route(null, null, null, null, null);

        em.persist(r);
        Route rPersisted = em.find(Route.class, r.getId());

        Assert.assertNotNull(rPersisted);
    }

    @Test
    public void testTicketPersistence(){
        Ticket t = new Ticket(null, null);

        em.persist(t);
        Ticket tPersisted = em.find(Ticket.class, t.getId());

        Assert.assertNotNull(tPersisted);
    }
    //todo: fix tests when validation is done
}

