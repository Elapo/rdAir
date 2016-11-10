package com.realdolmen.rdAir.repositories;

import com.realdolmen.rdAir.domain.Customer;
import com.realdolmen.rdAir.domain.Order;
import com.realdolmen.rdAir.domain.Ticket;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.ws.rs.NotFoundException;
import java.util.List;

public class OrderRepository { //todo test
    @PersistenceContext
    EntityManager em;

    PersistenceUnitUtil util;

    UserRepository ur;

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
        catch (NoResultException e) {
            return null;
        }
    }

    public int getPaidOrderCount(){
        Long l = (long)em.createQuery("select count(o) from Order o where o.status='accepted'").getSingleResult();
        return l.intValue();
    }

    public int getPendingOrderCount(){
        Long l = (long)em.createQuery("select count(o) from Order o where o.status='pending'").getSingleResult();
        return l.intValue();
    }

    public double getAverageOrderPrice(){
        try {
            return (double) em.createQuery("select avg(o.orderPrice) from Order o").getSingleResult();//todo date
        }
        catch (NullPointerException e){
            return 0;
        }
    }

    public double getMinOrderPrice(){
        try {
            return (double)em.createQuery("select min(o.orderPrice) from Order o").getSingleResult();
        }
        catch (NullPointerException e ){
            return 0;
        }
    }

    public double getMaxOrderPrice(){
        try {
            return (double)em.createQuery("select max(o.orderPrice) from Order o").getSingleResult();
        }
        catch (NullPointerException e){
            return 0;
        }
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

    public boolean delete(int id) {
        Order o = em.getReference(Order.class, id);
        if (o != null) {
            em.remove(o);
            return true;
        }
        return false;
    }
}
