package com.realdolmen.rdAir.repositories;

import com.realdolmen.rdAir.domain.Airline;
import com.realdolmen.rdAir.domain.Airline;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.List;

@Stateless
public class AirlineRepository {//todo test

    @PersistenceContext
    EntityManager em;

    public Airline save(Airline airline){
        em.merge(airline);
        return airline;
    }

    public Airline findById(int id){
        return em.find(Airline.class, id);
    }

    @SuppressWarnings(value = "all")
    public List<Airline> findByName(String name){
        Query q = em.createQuery("select a from Airline a where a.airlineName=?1");
        return q.setParameter(1, name).getResultList();
    }

    @SuppressWarnings(value = "all")
    public List<Airline> getAllAirlines(){
        try {
            return em.createQuery("select a from Airline a").getResultList();
        }
        catch (NoResultException e){
            return null;
        }
    }

    @SuppressWarnings(value = "all")
    public boolean delete(int id) {
        Airline l = em.getReference(Airline.class, id);
        try {
            if(l != null) {
                em.remove(l);
                return true;
            }
        }
        catch (EntityNotFoundException e){
            return false;
        }
        return false;
    }
}
