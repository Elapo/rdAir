package com.realdolmen.rdAir.controllers;

import com.realdolmen.rdAir.domain.Customer;
import com.realdolmen.rdAir.domain.Order;
import com.realdolmen.rdAir.domain.Ticket;
import com.realdolmen.rdAir.util.PriceCalculator;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.mail.Session;
import java.util.Date;

@ManagedBean
@ViewScoped
public class BookingBean {
    @ManagedProperty(value = "#{loginBean}")
    LoginBean session;

    Order bookingOrder;

    public String creditCard;
    public Date expirationDate;

    @PostConstruct
    public void init(){
        bookingOrder.getTickets().addAll(session.getBooking().getTickets());
        bookingOrder.setOrderPrice(bookingOrder.getOrderPrice() + session.getBooking().getOrderPrice());
    }

    public String doPayment(String method){
        if(method.equals("Credit")) {
            bookingOrder.setOrderPrice(bookingOrder.getOrderPrice() * PriceCalculator.RDAIR_CREDITCARD_PROMO);
            if (expirationDate != null && expirationDate.after(new Date()) && !creditCard.trim().isEmpty()) {
                bookingOrder.setStatus("Accepted");
                Customer c = (Customer)session.getUser();
                c.getOrders().add(bookingOrder);
                session.getUr().save(c);
                return "pretty:view-thanks";
            }
        }
        else if(method.equals("Transfer")){
            bookingOrder.setStatus("Pending");
            Customer c = (Customer) session.getUser();
            c.getOrders().add(bookingOrder);
            session.getUr().save(c);
            return "pretty:view-thanks";
        }
        return "pretty:view-index";
    }

    private void sendMail(){
        String to = session.getUser().getEmail();

        Session session = Session.getInstance(null, null);
    }
}
