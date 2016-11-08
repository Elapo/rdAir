package com.realdolmen.rdAir.repositories;

import com.realdolmen.rdAir.domain.Airline;
import com.realdolmen.rdAir.domain.Customer;
import com.realdolmen.rdAir.domain.RDEmployee;
import com.realdolmen.rdAir.utilities.persistence.JpaPersistenceTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.util.List;

public class TestUserRepository extends JpaPersistenceTest {

    UserRepository ur;
    EntityManager em;
    @Before
    public void init(){
        em = entityManager();
        ur = new UserRepository();
        ur.em = em;
    }

    @Test
    public void testPersonRepositoryCount(){
        long cnt = ur.getCount(Customer.class);
        long testCount = em.createQuery("select count(c) from Customer c").getFirstResult();

        Assert.assertEquals(testCount,cnt);
    }

    @Test
    public void testUserRepositorySave(){
        Customer c = new Customer("Frederik", "Van Herbruggen", "FCL 34", "0474416357", "email@email.com","abc", null);
        c = (Customer)ur.save(c);

        Customer cPersistsed = em.find(Customer.class, c.getId());
        Assert.assertNotNull(cPersistsed);
        Assert.assertEquals(c.getFirstName(), cPersistsed.getFirstName());
    }

    @Test
    public void testUserRepositoryFindById(){
        RDEmployee rde = new RDEmployee("Frederik", "Van Herbruggen", "FCL 34", "0474416357", "fre@email.com","abc", null);
        em.persist(rde);

        RDEmployee rdePersisted = ur.findById(rde.getId(), RDEmployee.class);

        Assert.assertNotNull(rdePersisted);
        Assert.assertEquals(rde, rdePersisted);
    }

    @Test
    public void testUserRepositoryFindByEmail(){
        Airline a = new Airline("Frederik", "Van Herbruggen", "FCL 34", "0474416357", "email@email.com", "freAir", "http://freAir.com","abc", null, null);
        em.persist(a);

        Airline aPersisted = (Airline)ur.getUserByEmail("email@email.com");

        Assert.assertNotNull(aPersisted);
        Assert.assertNotNull(aPersisted.getWebsite());
        Assert.assertEquals(a, aPersisted);
    }

    @Test
    public void testUserRepositoryGetPerPage(){
        RDEmployee rde = new RDEmployee("Frederik", "Van Herbruggen", "FCL 34", "0474416357", "email@email.com","abc", null);
        RDEmployee rde2 = new RDEmployee("Frederick", "Van Herbruggen", "FCL 34", "0474416357", "email@email.com","abc", null);

        em.persist(rde);
        em.persist(rde2);

        List<RDEmployee> list = ur.getPerPage(0, 10, RDEmployee.class);

        Assert.assertNotNull(list);
        Assert.assertNotEquals(list.size(), 0); //check if list is longer than 0, which it should be after inserting 2 values
    }
}
