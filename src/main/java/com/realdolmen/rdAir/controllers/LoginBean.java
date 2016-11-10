package com.realdolmen.rdAir.controllers;

import com.realdolmen.rdAir.domain.Order;
import com.realdolmen.rdAir.domain.Ticket;
import com.realdolmen.rdAir.domain.User;
import com.realdolmen.rdAir.repositories.UserRepository;
import com.realdolmen.rdAir.util.PriceCalculator;
import org.mindrot.jbcrypt.BCrypt;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Date;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable{
    private User user;

    @Inject
    private UserRepository ur;

    private boolean loggedIn = false;

    private String email;
    private String password;

    private Order booking;

    public String doLogin() {
        if (user == null) {
            user = ur.getUserByEmail(email);
        }
        if(user != null) System.out.println("comparing " + password +" with " + user.getPasswordHash());
        else System.out.println("Could not find user!");
        if(user != null && BCrypt.checkpw(password, user.getPasswordHash())){//if user is found and pass is correct, user is logged in
            loggedIn = true;
            password = ""; //get rid of pass in mem
            System.out.println("Logged in user "+ user.getFirstName());
            return "pretty:view-index";
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "{Messages.invalid.login}", "{Message.invalid.login}"));
        return "";
    }

    public String doLogout(){
        loggedIn = false;
        return "pretty:view-home"; //todo redirect to home
    }

    public void addToOrder(Ticket t){
        if(user.getClass().getSimpleName().equals("Customer")){
            if (booking == null) {
                booking = new Order(null, new Date());
            }
            booking.getTickets().add(t);
            t.setSalesPrice(PriceCalculator.calculatePrice(t));
            booking.setOrderPrice(booking.getOrderPrice() + t.getSalesPrice());
        }
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword(){
        return this.password;
    }

    public Order getBooking() {
        return booking;
    }

    public UserRepository getUr() {
        return ur;
    }
}
