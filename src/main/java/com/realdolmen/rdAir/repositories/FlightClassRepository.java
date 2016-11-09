package com.realdolmen.rdAir.repositories;

import com.realdolmen.rdAir.domain.FlightClass;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class FlightClassRepository {//todo test
    @PersistenceContext
    EntityManager em;

    public FlightClass save(FlightClass fc){
        em.persist(fc);
        return fc;
    }

    public FlightClass findById(int id){
        return em.find(FlightClass.class, id);
    }

    @SuppressWarnings(value = "all")
    public List<FlightClass> findByName(String name){
        Query sql = em.createQuery("select f from FlightClass f where f.name=?1");
        try{
            return sql.setParameter(1, name).getResultList();
        }
        catch (NoResultException e){
            return null;
        }
    }

    public boolean delete(int id){
        FlightClass fc = em.getReference(FlightClass.class, id);
        if (fc != null) {
            em.remove(fc);
            return true;
        }
        return false;
    }
}
