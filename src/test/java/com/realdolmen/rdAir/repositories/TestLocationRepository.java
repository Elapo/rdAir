package com.realdolmen.rdAir.repositories;

import com.realdolmen.rdAir.domain.Location;
import com.realdolmen.rdAir.domain.Region;
import com.realdolmen.rdAir.utilities.persistence.JpaPersistenceTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;

public class TestLocationRepository extends JpaPersistenceTest {

    EntityManager em;
    LocationRepository lr;

    @Before
    public void init(){
        lr = new LocationRepository();
        em = entityManager();
        lr.em = em;
    }

    @Test
    public void testLocationRepositorySave(){
        Location l = new Location("one", "two", new Region("three"));
        lr.save(l);

        Assert.assertNotNull(em.find(Location.class, l.getId()));
        Assert.assertEquals(em.find(Location.class, l.getId()), l);
    }

    @Test
    public void testLocationRepositoryFindById(){
        Location l =new Location("one", "two", new Region("three"));
        em.persist(l);

        Assert.assertNotNull(lr.findById(l.getId()));
        Assert.assertEquals(lr.findById(l.getId()), l);
    }

    @Test
    public void testLocationRepositoryFindByName(){
        Location l =new Location("oner", "two", new Region("three"));
        em.persist(l);

        Assert.assertNotNull(lr.findByName("oner"));
        Assert.assertEquals(l, lr.findByName("oner").get(0));
    }

    @Test
    public void testLocationRepositoryGetPerPage(){
        Location l =new Location("one", "two", new Region("three"));
        em.persist(l);

        Assert.assertNotNull(lr.getPerPage(1,10));
        Assert.assertNotEquals(0, lr.getPerPage(1,10));
    }

    @Test
    public void testLocationRepositoryDelete(){
        Location l =new Location("oner", "two", new Region("three"));
        em.persist(l);
        int id = l.getId();

        Assert.assertNotNull(em.find(Location.class, id));
        lr.delete(id);
        Assert.assertNull(em.find(Location.class, id));
    }

    @Test
    public void testLocationRepositoryDeleteFail(){
        Assert.assertFalse(lr.delete(124345434));
    }
}
