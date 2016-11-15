package com.realdolmen.rdAir.repositories;

import com.realdolmen.rdAir.domain.Region;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Frederik on 12/11/2016.
 */
@Stateless
public class RegionRepository implements Serializable{

    @PersistenceContext
    EntityManager em;

    @SuppressWarnings(value = "all")
    public List<String> getAllRegionNames(){
        try {
            return em.createQuery("select r.name from Region r").getResultList();
        }
        catch (NoResultException e){
            return null;
        }
    }

    @SuppressWarnings(value = "all")
    public List<Region> getAllRegions(){
        try {
            return em.createQuery("select r from Region r").getResultList();
        }
        catch (NoResultException e){
            return null;
        }
    }

    public Region getRegionByName(String name){
        try {
            Query sql = em.createQuery("select r from Region r where r.name=:name");
            return (Region) sql.setParameter("name", name).getSingleResult();
        }
        catch (NoResultException e){
            return null;
        }
    }
}
