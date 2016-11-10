package com.realdolmen.rdAir.repositories;

import com.realdolmen.rdAir.domain.Location;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class LocationRepository {//todo test

    @PersistenceContext
    EntityManager em;

    public Location save(Location loc){
        em.persist(loc);
        return loc;
    }

    public Location findById(int id){
        return em.find(Location.class, id);
    }

    @SuppressWarnings(value = "all")
    public List<Location> findByName(String name){
        Query q = em.createQuery("select l from Location l where l.airportName=?1");
        return q.setParameter(1, name).getResultList();
    }

    @SuppressWarnings(value = "all")
    public List<Location> getPerPage(int currentPage, int perPage){
        if(currentPage <= 0 || perPage <= 0) return null;
        return em.createQuery("select l from Location l")
                .setFirstResult((currentPage-1)*perPage)
                .setMaxResults(perPage)
                .getResultList();
    }

    @SuppressWarnings(value = "all")
    public boolean delete(int id) {
        Location l = em.getReference(Location.class, id);
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
