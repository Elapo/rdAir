package com.realdolmen.rdAir.repositories;

import com.realdolmen.rdAir.domain.Ticket;
import com.realdolmen.rdAir.utilities.persistence.JpaPersistenceTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class TestTicketRepository extends JpaPersistenceTest {

    EntityManager em;

    TicketRepository tr;

    @Before
    public void init(){
        em = entityManager();
        tr = new TicketRepository();
        tr.em = em;
    }

    @Test
    public void testTicketRepositorySave(){
        Ticket t = new Ticket(null, null);
        tr.save(t);

        Ticket tPersisted = em.find(Ticket.class,t.getId());

        Assert.assertNotNull(tPersisted);
        Assert.assertEquals(t, tPersisted);
    }

    @Test
    public void testTicketRepositoryFindById(){
        Ticket t = new Ticket(null,null);

        em.persist(t);

        Ticket tPersisted = tr.findById(t.getId());

        Assert.assertNotNull(tPersisted);
        Assert.assertEquals(t, tPersisted);
    }

    @Test
    public void testTicketRepositoryDelete(){
        Ticket t = new Ticket(null, null);
        em.persist(t);
        int id = t.getId();

        Assert.assertNotNull(em.find(Ticket.class, id));
        tr.delete(id);
        Assert.assertNull(em.find(Ticket.class, id));
    }
}
