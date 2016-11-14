package com.realdolmen.rdAir.util;

import com.realdolmen.rdAir.domain.Flight;
import com.realdolmen.rdAir.domain.FlightClass;
import com.realdolmen.rdAir.domain.PriceModifier;
import com.realdolmen.rdAir.domain.Ticket;
import org.hibernate.Hibernate;

import java.util.Date;

public class PriceCalculator {

    public static final double RDAIR_CREDITCARD_PROMO = 0.95;

        //todo calculate order price for volume discounts

    public static double calculatePrice(Ticket t){
        FlightClass fc = t.getFlightClass();
        Flight f = t.getFlight();
        Hibernate.initialize(fc);
        Hibernate.initialize(f);

        double basePrice = fc.getPrice();
        Hibernate.initialize(f.getRdAirModifier());
        if(!f.getRdAirModifier().isPercent()){
            //the RD modifier is not a percentage, so it is a flat amount
            //meaning this is the final price
            //todo check if price is lower than airline price
            return t.getFlight().getRdAirModifier().getAmount();
        }
        else{
            Hibernate.initialize(f.getRoute());
            Hibernate.initialize(f.getRoute().getModifiers());
            Hibernate.initialize(f.getRoute().getRdModifiers());
            //get all modifiers from db
            basePrice = calcAirlinePrice(t);
            for(PriceModifier pm: f.getRoute().getRdModifiers()){
                if(isActive(pm)){
                    basePrice += pm.getAmount();
                }
            }
            for(PriceModifier pm: f.getRdAirModifiers()){
                if(isActive(pm) && pm.isPercent()){
                    basePrice *= pm.getAmount();
                }
            }
            return basePrice;
        }
    }

    public static double calcAirlinePrice(Ticket t){
        FlightClass fc = t.getFlightClass();
        Flight f = t.getFlight();
        Hibernate.initialize(fc);
        Hibernate.initialize(f);
        Hibernate.initialize(f.getRoute());
        Hibernate.initialize(f.getPriceModifiers());

        double price = fc.getPrice();
        Hibernate.initialize(f.getRoute().getModifiers());
        for (PriceModifier pm : f.getRoute().getRdModifiers()){
            if(isActive(pm) && pm.isPercent()){
                price = price*pm.getAmount();
            }
            else if(isActive(pm) && ! pm.isPercent()){
                price = price + pm.getAmount();
            }
        }
        for (PriceModifier pm : f.getPriceModifiers()){
            if(isActive(pm) && pm.isPercent()){
                price = price*pm.getAmount();
            }
            else if(isActive(pm) && !pm.isPercent()){
                price = price + pm.getAmount();
            }
        }
        return price;
    }

    private static boolean isActive(PriceModifier pm){
        Date now = new Date();
        if(pm.getStartDate() == null){ //it's time-based
            if(pm.getStartDate().before(now) && pm.getEndDate().after(now)){
                return true;
            }
        }
        else {
            if(pm.getStartTime().getTime() < now.getTime() && pm.getEndTime().getTime() > now.getTime()){//fixme
                return true;
            }
        }
        return false;
    }
}
