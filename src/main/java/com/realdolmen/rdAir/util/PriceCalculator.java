package com.realdolmen.rdAir.util;

import com.realdolmen.rdAir.domain.Flight;
import com.realdolmen.rdAir.domain.FlightClass;
import com.realdolmen.rdAir.domain.PriceModifier;
import com.realdolmen.rdAir.domain.Ticket;
import org.hibernate.Hibernate;

import java.util.Date;

public abstract class PriceCalculator {
    public double calculatePrice(Ticket t){
        FlightClass fc = t.getFlightClass();
        Flight f = t.getFlight();
        Hibernate.initialize(fc);
        Hibernate.initialize(f);

        double basePrice = fc.getPrice();
        if(!t.getFlight().getRdAirModifier().isPercent()){
            //the RD modifier is not a percentage, so it is a flat amount
            //meaning this is the final price
            //todo check if price is lower than airline price
            return t.getFlight().getRdAirModifier().getAmount();
        }
        return 0;
    }

    private boolean isActive(PriceModifier pm){
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
