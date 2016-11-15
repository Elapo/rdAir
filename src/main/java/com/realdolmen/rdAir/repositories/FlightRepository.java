package com.realdolmen.rdAir.repositories;

import com.realdolmen.rdAir.domain.Flight;
import com.realdolmen.rdAir.domain.Route;

import javax.ejb.Stateless;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Stateless
public class FlightRepository {

    @PersistenceContext
    EntityManager em;

    public FlightRepository(){
    }

    public Flight save(Flight flight){
        em.persist(flight);
        return flight;
    }


    public Flight findById(int id){
        return em.find(Flight.class, id);
    }

    @SuppressWarnings(value = "all")
    public List<Flight> getFlightsPerPage(int page, int perpage)
    {
        if(page <= 0 || perpage <= 0) return null;
        Query sql = em.createQuery("select f from Flight f").setMaxResults(perpage).setFirstResult((page-1)*perpage);
        try{
            return sql.getResultList();
        }
        catch (NoResultException e){
            return null;
        }
    }

    @SuppressWarnings(value = "all")
    public boolean delete(int id){
        Flight flight = em.getReference(Flight.class, id);
        try {
            if (flight != null) {
                em.remove(flight);
                return true;
            }
        }
        catch (EntityNotFoundException e){
            return false;
        }
        return false;
    }

}
