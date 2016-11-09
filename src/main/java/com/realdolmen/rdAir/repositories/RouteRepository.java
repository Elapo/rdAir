package com.realdolmen.rdAir.repositories;

import com.realdolmen.rdAir.domain.Route;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class RouteRepository {

    @PersistenceContext
    EntityManager em;

    public long getCount(){
        return em.createQuery("select count(r) from Route r").getFirstResult();
    }

    public Route save(Route r){
        em.persist(r);
        return r;
    }

    public Route findById(int id){
        return em.find(Route.class, id);
    }

    @SuppressWarnings(value = "all")
    public List<Route> getPerPage(int currentPage, int perPage){
        return em.createQuery("select r from Route r").setFirstResult(currentPage*perPage).setMaxResults(perPage).getResultList();
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
