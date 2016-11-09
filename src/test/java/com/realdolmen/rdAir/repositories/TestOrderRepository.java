package com.realdolmen.rdAir.repositories;

import com.realdolmen.rdAir.domain.Order;
import com.realdolmen.rdAir.utilities.persistence.JpaPersistenceTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.matchers.Or;

import javax.persistence.EntityManager;
import java.util.Date;

public class TestOrderRepository extends JpaPersistenceTest {

    EntityManager em;

    OrderRepository or;

    @Before
    public void init(){
        em = entityManager();
        or = new OrderRepository();
        or.em = em;
    }

    @Test
    public void testOrderRepositorySave(){
        Order o = new Order(null, new Date());
        or.save(o);

        Assert.assertNotNull(em.find(Order.class, o.getId()));
        Assert.assertEquals(em.find(Order.class, o.getId()), o);
    }

    @Test
    public void testOrderRepositoryFindById(){
        Order o = new Order(null, new Date());
        em.persist(o);

        Assert.assertNotNull(or.findById(o.getId()));
        Assert.assertEquals(o, or.findById(o.getId()));
    }

    @Test
    public void testOrderRepositoryFindByStatus(){
        Order o = new Order(null, new Date());
        o.setStatus("test");
        em.persist(o);

        Assert.assertNotNull(or.findByStatus("test"));
        Assert.assertEquals(or.findByStatus("test").get(0), o);
    }

    @Test
    public void testOrderRepositoryGetPaidOrderCount(){
        Order o = new Order(null, new Date());
        o.setStatus("accepted");
        em.persist(o);

        Assert.assertEquals(1, or.getPaidOrderCount());
    }
    @Test
    public void testOrderRepositoryGetPendingOrderCount(){
        Order o = new Order(null, new Date());
        o.setStatus("pending");
        em.persist(o);

        Assert.assertEquals(1, or.getPendingOrderCount());
    }
    @Test
    public void testOrderRepositoryGetAllOrders(){
        Order o = new Order(null, new Date());
        em.persist(o);

        Assert.assertNotEquals(0,or.getAllOrders().size());
    }

    @Test
    public void testOrderRepositoryDelete(){
        Order o = new Order(null, new Date());
        em.persist(o);
        int id=o.getId();

        Assert.assertNotNull(em.find(Order.class, id));
        or.delete(id);
        Assert.assertNull(em.find(Order.class, id));
    }
}
