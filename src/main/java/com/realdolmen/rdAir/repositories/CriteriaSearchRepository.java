package com.realdolmen.rdAir.repositories;

import com.realdolmen.rdAir.domain.Flight;
import com.realdolmen.rdAir.services.FlightSearchSupplier;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

@Stateless
public class CriteriaSearchRepository implements FlightSearchSupplier {

    @PersistenceContext
    EntityManager em;

    @Override
    @SuppressWarnings(value = "all")
    public List<Flight> searchFlights(int seats, String fClass, String airComp, String dep, String dest, String Region, Date departureDate) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Flight> query = builder.createQuery(Flight.class);
        Root<Flight> rt = query.from(Flight.class);
        return null;
    }
}
