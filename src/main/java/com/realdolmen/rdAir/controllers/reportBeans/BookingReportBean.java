package com.realdolmen.rdAir.controllers.reportBeans;

import com.realdolmen.rdAir.domain.Order;
import com.realdolmen.rdAir.domain.Ticket;
import com.realdolmen.rdAir.repositories.OrderRepository;
import com.realdolmen.rdAir.util.PriceCalculator;
import org.hibernate.Hibernate;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

//maybe move to backend, generate table instead
public class BookingReportBean {

    @Inject
    OrderRepository or;

    private double averagePrice;

    public int getAmountPaidTickets(){
        return or.getPaidOrderCount();

    }

    public int getAmountPendingTickets(){
        return or.getPendingOrderCount();
    }

    public double calcAveragePrice(){
        List<Order> orders = or.getAllOrders();
        List<Double> prices = new ArrayList<>();
        double average = 0;
        if(orders != null){
            for(Order o: orders){
                for(Ticket t: o.getTickets()){
                    Hibernate.initialize(t);
                    prices.add(PriceCalculator.calculatePrice(t));//add all prices to lis
                }
            }
            if(!prices.isEmpty()) {
                for (Double price : prices) {
                    average += price;
                }
                averagePrice = (average / prices.size());
                return averagePrice;
            }
        }
        return 0;
    }

    public double getCheapestSale(){
        return 0;
    }

    public double getMostExpensiveSale(){
        return 0;
    }

    public double getAverageProfitMargin(){
        return 0;
    }

    public double getAveragePrice() {
        return averagePrice;
    }
}
