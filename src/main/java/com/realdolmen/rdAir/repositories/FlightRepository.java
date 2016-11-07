package com.realdolmen.rdAir.repositories;

import com.realdolmen.rdAir.domain.Flight;
import com.realdolmen.rdAir.domain.Route;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class FlightRepository {

    @PersistenceContext
    EntityManager em;

    public Flight save(Flight flight){
        em.persist(flight);
        return flight;
    }

    @SuppressWarnings(value = "all")
    public List<Flight> getByRoute(Route route){
        return em.createQuery("select f from Flight f where Route r = route").getResultList();
    }
}
