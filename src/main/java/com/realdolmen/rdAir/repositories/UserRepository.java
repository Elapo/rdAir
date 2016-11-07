package com.realdolmen.rdAir.repositories;

import com.realdolmen.rdAir.domain.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
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

    @SuppressWarnings(value = "all")
    public <T extends User> T getUserByEmail(String email, Class<T> type){
        Query sql = em.createQuery("select o from "+type.getSimpleName()+" o where o.email = ?1");
        return (T)sql.setParameter(1, email).getSingleResult();
    }

    @SuppressWarnings(value = "all")
    public <T extends User> List<T> getPerPage(int currentPage, int perPage, Class<T> type){
        return em.createQuery("select o from "+type.getSimpleName()+" o")
                .setFirstResult(currentPage*perPage)
                .setMaxResults(perPage)
                .getResultList();
    }


}
