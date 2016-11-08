package com.realdolmen.rdAir.repositories;

import com.realdolmen.rdAir.domain.Ticket;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class TicketRepository { //todo test
    @PersistenceContext
    EntityManager em;

    public Ticket save(Ticket ticket){
        em.persist(ticket);
        return ticket;
    }

    public Ticket findById(int id){
        return em.find(Ticket.class, id);
    }

}
