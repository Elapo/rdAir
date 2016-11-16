package com.realdolmen.rdAir.repositories;

import com.realdolmen.rdAir.domain.Airline;
import com.realdolmen.rdAir.domain.Route;
import org.hibernate.Hibernate;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class RouteRepository {

    @PersistenceContext
    EntityManager em;

    public long getCount(){
        return em.createQuery("select count(r) from Route r").getFirstResult();
    }

    public Route save(Route r){
        em.merge(r);
        return r;
    }

    public Route findById(int id){
        return em.find(Route.class, id);
    }

    @SuppressWarnings(value = "all")
    public List<Route> getAllForAirline(Airline a){
        try {
            Query sql = em.createQuery("select r from Route r where r.airline.id=?1");
            if(a != null) {
                List<Route> routes =sql.setParameter(1, a.getId()).getResultList();
                for (Route route : routes) {
                    Hibernate.initialize(route.getDepartureLocation());
                    Hibernate.initialize(route.getDestination());
                }
                return routes;
            }
            return null;
        }
        catch (NoResultException e){
            return null;
        }
    }

    @SuppressWarnings(value = "all")
    public List<Route> getPerPage(int currentPage, int perPage){
        if(currentPage <= 0 || perPage <= 0) return null;
        return em.createQuery("select r from Route r")
                .setFirstResult((currentPage-1)*perPage)
                .setMaxResults(perPage)
                .getResultList();
    }

    public boolean delete(int id){
        Route r= em.getReference(Route.class, id);
        if(r!=null){
            em.remove(r);
            return true;
        }
        return false;
    }
}
