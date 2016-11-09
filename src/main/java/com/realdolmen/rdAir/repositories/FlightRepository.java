package com.realdolmen.rdAir.repositories;

import com.realdolmen.rdAir.domain.Flight;
import com.realdolmen.rdAir.domain.Route;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Stateless
public class FlightRepository {

    @PersistenceContext
    EntityManager em;
    //CriteriaBuilder cBuilder;

    public FlightRepository(){
        //cBuilder = em.getCriteriaBuilder();
    }

    public Flight save(Flight flight){
        em.persist(flight);
        return flight;
    }

    public boolean delete(int id){
        Flight f =em.getReference(Flight.class, id);
        if (f != null) {
            em.remove(f);
            return true;
        }
        return false;
    }

}
