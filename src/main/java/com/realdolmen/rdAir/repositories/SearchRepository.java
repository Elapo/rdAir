package com.realdolmen.rdAir.repositories;

import com.realdolmen.rdAir.domain.Flight;
import com.realdolmen.rdAir.domain.Ticket;
import com.realdolmen.rdAir.services.FlightSearchSupplier;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Frederik on 09/11/2016.
 */
public class SearchRepository implements FlightSearchSupplier {
    //todo list:
    /*
        1. get a simple search running using a long query with ands and null checks
        2. Price modifier per flight
        3. Test cascades in persistence tests
     */
    @PersistenceContext
    EntityManager em;

    @Override
    @SuppressWarnings(value = "all")
    public List<Ticket> searchFlights(int seats, String fClass, String airComp, String dep, String dest, String region, Date departureDate) {
        List<String> availableParams = new ArrayList<>();
        String query = "select t from Ticket t where ";
        if(!airComp.trim().isEmpty()){
            query += "t.flight.route.airline.airlineName=:aircomp ";
            availableParams.add("airline");
        }
        if(seats != 0 && fClass != null){
            query += "and t.flightClass.availableSeatCount>=:seats and t.flightClass.name=:fClass ";
            availableParams.add("class");
        }
        if(!dep.trim().isEmpty() && !dest.trim().isEmpty()){
            query+= "and t.flight.route.departureLocation.airportName=:depart and t.flight.route.destination.airportName=:dest ";
            availableParams.add("location");
        }
        else if(!region.trim().isEmpty()) {
            query += "and t.flight.route.destination.region.name=:region ";
            availableParams.add("region");
        }
        if(departureDate != null){
            query += "and t.flight.departureDate=:departureDate";
            availableParams.add("depdate");
        }

        Query sql = em.createQuery(query);
        //setparams
        if(availableParams.contains("airline")){
            sql.setParameter("aircomp", airComp);
        }
        if(availableParams.contains("class")){
            sql.setParameter("seats", seats);
            sql.setParameter("fClass", fClass);
        }
        if(availableParams.contains("location")){
            sql.setParameter("depart", dep);
            sql.setParameter("dest", dest);
        }
        if(availableParams.contains("region")){
            sql.setParameter("region", region);
        }
        if (availableParams.contains("depdate")){
            sql.setParameter("departureDate", departureDate);
        }

        return sql.getResultList();
    }
}
