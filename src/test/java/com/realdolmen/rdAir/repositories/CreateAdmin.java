package com.realdolmen.rdAir.repositories;

import com.realdolmen.rdAir.domain.RDEmployee;
import com.realdolmen.rdAir.repositories.UserRepository;
import com.realdolmen.rdAir.utilities.persistence.JpaPersistenceTest;
import org.junit.Before;
import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.EntityManager;

/**
 * Created by Frederik on 15/11/2016.
 */
public class CreateAdmin extends JpaPersistenceTest {

    EntityManager em;
    UserRepository ur;

    @Before
    public void init(){
        em = entityManager();
        ur = new UserRepository();
        ur.em = em;
    }

    @Test
    public void createAdminAccount(){
        RDEmployee emp = new RDEmployee("Emp", "Loyee", "Adress", "0123456", "realdolmenair@gmail.com", BCrypt.hashpw("abc123", BCrypt.gensalt()), null);
        ur.save(emp);
    }
}
