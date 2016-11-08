package com.realdolmen.rdAir.repositories;

import com.realdolmen.rdAir.domain.Order;
import com.realdolmen.rdAir.domain.Ticket;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.NotFoundException;
import java.util.List;

public class OrderRepository { //todo test
    @PersistenceContext
    EntityManager em;

    public Order save(Order order){
        em.persist(order);
        return order;
    }

    public Order findById(int id){
        return em.find(Order.class, id);
    }

    @SuppressWarnings(value = "all")
    public List<Order> findByStatus(String status){
        Query sql = em.createQuery("select o from Order o where o.status=?1");
        try{
            return sql.setParameter(1,status).getResultList();
        }
        catch (NotFoundException e) {
            return null;
        }
    }

    public int getPaidOrderCount(){
        return em.createQuery("select count(o) from Order o where o.status=\"paid\"").getFirstResult();
    }

    public int getPendingOrderCount(){
        return em.createQuery("select count(o) from Order o where o.status=\"pending\"").getFirstResult();
    }

    @SuppressWarnings(value = "all")
    public List<Order> getAllOrders(){
        try{
            return em.createQuery("select o from Order o").getResultList();
        }
        catch (NoResultException e){
            return null;
        }
    }
}
