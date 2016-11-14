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
    public <T extends User> T findByName(String name, Class<T> type){
        Query sql;
        String test = type.getSimpleName();
        if(!type.getSimpleName().equals("Airline")) return null;
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
        if(currentPage <= 0 || currentPage <= 0) return null;
        return em.createQuery("select o from "+type.getSimpleName()+" o")
                .setFirstResult((currentPage-1)*perPage)
                .setMaxResults(perPage)
                .getResultList();
    }

    @SuppressWarnings(value = "all")
    public <T extends User> List<T> getAllUsers(Class<T> type) {
        try{
            return em.createQuery("select o from " + type.getSimpleName() + " o").getResultList();
        }
        catch (NoResultException e){
            return null;
        }
    }


        public <T extends User> boolean delete(int id, Class<T> type){
        User u = em.getReference(type, id);
        if(u!=null) {
            em.remove(em.getReference(type, id));
            return true;
        }
        return false;
    }

    //todo get per type paginated
}
