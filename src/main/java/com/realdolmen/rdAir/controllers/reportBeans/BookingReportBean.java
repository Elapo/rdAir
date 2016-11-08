package com.realdolmen.rdAir.controllers.reportBeans;

import com.realdolmen.rdAir.domain.Order;
import com.realdolmen.rdAir.domain.Ticket;
import com.realdolmen.rdAir.repositories.OrderRepository;
import org.hibernate.Hibernate;

import javax.inject.Inject;
import java.util.List;

public class BookingReportBean {

    @Inject
    OrderRepository or;

    public int getAmountPaidTickets(){
        return or.getPaidOrderCount();

    }

    public int getAmountPendingTickets(){
        return or.getPendingOrderCount();
    }

    public double getAveragePrice(){
        List<Order> orders = or.getAllOrders();
        if(orders != null){
            for(Order o: orders){
                for(Ticket t: o.getTickets()){
                    Hibernate.initialize(t);
                }
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
}
