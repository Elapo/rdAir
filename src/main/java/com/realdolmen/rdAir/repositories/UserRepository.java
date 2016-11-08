package com.realdolmen.rdAir.repositories;

import com.realdolmen.rdAir.domain.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class UserRepository {

    @PersistenceContext
    EntityManager em;

    public <T> long getCount(Class<T> type){
        return em.createQuery("select count(*) from "+type.getSimpleName()+" t").getFirstResult();
    }

    public User save(User user){
        em.persist(user);
        return user;
    }

    public <T extends User> T findById(int id, Class<T> type){
        return em.find(type, id);
    }

    public User getUserByEmail(String email){
        Query sql = em.createQuery("select o from User o where o.email = ?1");;
        User u;
        try{
            u = (User)sql.setParameter(1, email).getSingleResult();
        }
        catch (NoResultException e) {
            return null;
        }
        return u;
    }

    @SuppressWarnings(value = "all")
    public <T extends User> T findByName(String name, Class<T> type){//todo test
        Query sql;
        if(type.getSimpleName() != "Airline") sql = em.createQuery("select u from " +type.getSimpleName()+" u where u.lastName=?1");
        else sql = em.createQuery("select u from Airline u where u.airlineName=?1");
        try {
            return (T)sql.setParameter(1,name).getSingleResult();
        }
        catch (NoResultException e){
            return null;
        }
    }

    @SuppressWarnings(value = "all")
    public <T extends User> List<T> getPerPage(int currentPage, int perPage, Class<T> type){
        return em.createQuery("select o from "+type.getSimpleName()+" o")
                .setFirstResult(currentPage*perPage)
                .setMaxResults(perPage)
                .getResultList();
    }


}
