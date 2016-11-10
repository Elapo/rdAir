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

    public double getSmallestOrder(){
        return or.getMinOrderPrice();
    }

    public double getLargestOrder(){
        return or.getMaxOrderPrice();
    }

    public double getAverageProfitMargin(){
        return 0;
    }// TODO: 10/11/2016  

    public double getAveragePrice() {
        return or.getAverageOrderPrice();
    }
}
