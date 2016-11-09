package com.realdolmen.rdAir.repositories;

import com.realdolmen.rdAir.domain.Customer;
import com.realdolmen.rdAir.domain.Route;
import com.realdolmen.rdAir.utilities.persistence.JpaPersistenceTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.util.List;

public class TestRouteRepository extends JpaPersistenceTest {

    EntityManager em;
    RouteRepository rp;

    @Before
    public void init(){
        em = entityManager();
        rp = new RouteRepository();
        rp.em = em;
    }

    @Test
    public void testRouteRepositoryCount(){
        long cnt = rp.getCount();
        long testCount = em.createQuery("select count(r) from Route r").getFirstResult();

        Assert.assertEquals(testCount,cnt);
    }

    @Test
    public void testRouteRepositorySave(){
        Route r = rp.save(new Route(null, null, null, null));

        Route persisted = em.find(Route.class, r.getId());

        Assert.assertNotNull(persisted);
        Assert.assertEquals(r, persisted);
    }

    @Test
    public void testRouteRepositoryFindById(){
        Route r = new Route(null,null,null,null);
        em.persist(r);

        Route persisted = rp.findById(r.getId());

        Assert.assertNotNull(persisted);
        Assert.assertEquals(r, persisted);
    }

    @Test
    public void testRouteRepositoryGetPerPage(){
        Route r = new Route(null,null,null,null);
        Route r2 = new Route(null,null,null,null);

        em.persist(r);
        em.persist(r2);

        List<Route> routes= rp.getPerPage(0,10);
        Assert.assertNotEquals(0, routes.size());
    }

    @Test
    public void testRouteRepositoryDelete(){
        Route r = new Route(null,null,null,null);
        em.persist(r);
        int id = r.getId();
        Route rPersisted = em.find(Route.class, id);
        Assert.assertNotNull(rPersisted);

        rp.delete(id);
        rPersisted = em.find(Route.class, id);
        Assert.assertNull(rPersisted);
    }
}
