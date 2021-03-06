package com.realdolmen.rdAir.repositories;

import com.realdolmen.rdAir.domain.Flight;
import com.realdolmen.rdAir.domain.Ticket;
import com.realdolmen.rdAir.services.FlightSearchSupplier;
import org.hibernate.Hibernate;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Frederik on 09/11/2016.
 */
@Stateless
public class SearchRepository implements Serializable{
    @PersistenceContext
    EntityManager em;

    public List<Flight> searchForFlights(int seats, String fClass, String airComp, String dep, String dest, String region, Date departureDate){
        List<String> availableParams = new ArrayList<>();
        String query = "select f from Flight f ";

        if(seats != 0 && fClass != null){
            query += "where exists (select fc from FlightClass fc where fc.name=:fClass and fc.availableSeatCount>=:seats) ";
            availableParams.add("class");
        }
        else query+= "where ";
        if(!airComp.trim().isEmpty()){
            if(availableParams.contains("class")) query+= "and ";
            query += "f.route.airline.airlineName=:aircomp ";
            availableParams.add("airline");
        }
        if(!dep.trim().isEmpty() && !dest.trim().isEmpty()){
            query+= "and f.route.departureLocation.airportName=:depart and f.route.destination.airportName=:dest ";
            availableParams.add("location");
        }
        else if(!region.trim().isEmpty()) {
            query += "and f.route.destination.region.name=:region ";
            availableParams.add("region");
        }
        if(departureDate != null){
            query += "and f.departureTime=:departureDate";
            availableParams.add("depdate");
        }
        System.out.println(query);
        Query sql = em.createQuery(query);
        //setparams
        if(availableParams.contains("airline")){
            sql.setParameter("aircomp", airComp);
        }
        if(availableParams.contains("location")){
            sql.setParameter("depart", dep);
            sql.setParameter("dest", dest);
        }
        if(availableParams.contains("region")){
            sql.setParameter("region", region);
        }
        if (availableParams.contains("depdate")){
            sql.setParameter("departureDate", departureDate, TemporalType.DATE);
        }
        if(availableParams.contains("class")){
            sql.setParameter("seats", seats);
            sql.setParameter("fClass", fClass);
        }
        List<Flight> results = sql.getResultList();
        if(results.size() == 0) System.out.println("no results");
        else System.out.println(results);

        for (Flight flight : results) {
            Hibernate.initialize(flight.getRoute());
            Hibernate.initialize(flight.getRoute().getDepartureLocation());
            Hibernate.initialize(flight.getRoute().getDestination());
            Hibernate.initialize(flight.getRoute().getAirline());
            Hibernate.initialize(flight.getRoute().getModifiers());
            Hibernate.initialize(flight.getRoute().getRdModifiers());
            Hibernate.initialize(flight.getAvailableClasses());
            Hibernate.initialize(flight.getPriceModifiers());
            Hibernate.initialize(flight.getRdAirModifiers());
        }

        return results;
    }

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
//        if(seats != 0 ){
//            availableParams.add("class");
//            switch (fClass){
//                case "First Class":
//                    query += "and t.flight.availableFirstClass>=:seats ";
//                    break;
//                case "Business Class":
//                    break;
//                case "Economy Class":
//                    break;
//                default:
//                    availableParams.remove(availableParams.size()-1);//remove if no class given
//                    break;
//            }
//        }
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
            //sql.setParameter("fClass", fClass);
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
