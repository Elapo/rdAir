package com.realdolmen.rdAir.repositories;

import com.realdolmen.rdAir.domain.Flight;
import com.realdolmen.rdAir.domain.Route;
import net.bootsfaces.render.H;
import org.hibernate.Hibernate;

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
        Flight f = em.find(Flight.class, id);
        Hibernate.initialize(f.getAvailableClasses());
        Hibernate.initialize(f.getRoute());
        Hibernate.initialize(f.getRoute().getDestination());
        Hibernate.initialize(f.getRoute().getDepartureLocation());
        Hibernate.initialize(f.getRoute().getAirline());
        Hibernate.initialize(f.getRoute().getModifiers());
        Hibernate.initialize(f.getRoute().getRdModifiers());
        Hibernate.initialize(f.getPriceModifiers());
        Hibernate.initialize(f.getRdAirModifier());
        Hibernate.initialize(f.getRdAirModifiers());
        return f;
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
