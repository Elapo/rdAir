package com.realdolmen.rdAir.util;

import com.realdolmen.rdAir.domain.*;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PriceCalculator {

    public static final double RDAIR_CREDITCARD_PROMO = 0.95;

        //todo calculate order price for volume discounts
    public static double calculatePrice(FlightClass fc){
        Flight f = fc.getFlight();
        Hibernate.initialize(f);

        double basePrice = fc.getPrice();
        Hibernate.initialize(f.getRdAirModifier());
        if(!f.getRdAirModifier().isPercent()){
            //the RD modifier is not a percentage, so it is a flat amount
            //meaning this is the final price
            //todo check if price is lower than airline price
            return fc.getFlight().getRdAirModifier().getAmount();
        }
        else{
            Hibernate.initialize(f.getRoute());
            Hibernate.initialize(f.getRoute().getModifiers());
            Hibernate.initialize(f.getRoute().getRdModifiers());
            //get all modifiers from db
            basePrice = calcAirlinePrice(fc);
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

    public static double calcAirlinePrice(FlightClass fc){
        Flight f = fc.getFlight();
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

    public static double getDiscountAmount(FlightClass fc) {
        Flight f = fc.getFlight();
        Hibernate.initialize(f);

        double discount = 0;
        Hibernate.initialize(f.getRdAirModifier());
        if(!f.getRdAirModifier().isPercent()){
            return fc.getFlight().getRdAirModifier().getAmount();
        }
        else{
            Hibernate.initialize(f.getRoute());
            Hibernate.initialize(f.getRoute().getModifiers());
            Hibernate.initialize(f.getRoute().getRdModifiers());
            for(PriceModifier pm: f.getRoute().getRdModifiers()){
                if(isActive(pm)){
                    discount += pm.getAmount();
                }
            }
            for(PriceModifier pm: f.getRdAirModifiers()){
                if(isActive(pm) && pm.isPercent()){
                    discount *= pm.getAmount();
                }
            }
            return discount;
        }
    }

    private void applyVolumeDiscount(Order o){ //call AFTER calculating the total
        List<PriceModifier> volumeDiscounts = new ArrayList<>();
        for(Ticket t: o.getTickets()){
            for(PriceModifier pm:t.getFlight().getPriceModifiers()){
                if(pm.isVolumeDiscount() && isActive(pm)){
                    if(o.getTickets().size() >= pm.getVolumeThreshold()){
                        o.setOrderPrice(o.getOrderPrice()*pm.getAmount());
                    }
                }
            }

        }
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
