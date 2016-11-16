package com.realdolmen.rdAir.repositories;

import com.realdolmen.rdAir.domain.FlightClass;
import org.hibernate.StaleObjectStateException;

import javax.persistence.*;
import java.util.List;

public class FlightClassRepository {
    @PersistenceContext
    EntityManager em;

    public FlightClass save(FlightClass fc){
        try{
            em.merge(fc);
            em.flush();
            return fc;
        }
        catch (StaleObjectStateException e){//todo test locking
            return null;
        }
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

    @SuppressWarnings(value = "all")
    public boolean delete(int id){
        FlightClass fc = em.getReference(FlightClass.class, id);
        try{
            if (fc != null) {
                em.remove(fc);
                return true;
            }
        }
        catch (EntityNotFoundException e){
            return false;
        }
        return false;
    }
}
