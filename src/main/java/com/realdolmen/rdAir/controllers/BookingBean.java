package com.realdolmen.rdAir.controllers;

import com.realdolmen.rdAir.domain.Ticket;
import com.realdolmen.rdAir.repositories.OrderRepository;
import com.realdolmen.rdAir.services.MailService;
import com.realdolmen.rdAir.util.PaymentValidator;
import org.hibernate.validator.constraints.NotEmpty;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.transaction.*;
import javax.validation.constraints.*;
import java.io.Serializable;

import static com.realdolmen.rdAir.util.PriceCalculator.RDAIR_CREDITCARD_PROMO;

@ManagedBean
@SessionScoped
public class BookingBean implements Serializable {

    private boolean isBooked;

    @Resource
    UserTransaction ut;

    @ManagedProperty(value = "#{loginBean}")
    LoginBean session;

    @Inject
    OrderRepository or;

    @NotEmpty(message="{Booking.creditcardNumber.null}")
    @Pattern(regexp="[0-9]{16}", message="{Booking.creditcardNumber.pattern}")
    private String creditcardNumber;

    @NotEmpty(message="{Booking.creditcardExpDate.null}")
    @Pattern(regexp="(0[1-9]|1[0-2])(1[7-9]|[2-9][0-9])", message="{Booking.creditcardExpDate.pattern}")
    private String creditcardExpDate;


    public String getCreditcardNumber() {
        return creditcardNumber;
    }

    public void setCreditcardNumber(String creditcardNumber) {
        this.creditcardNumber = creditcardNumber;
    }

    public String getCreditcardExpDate() {
        return creditcardExpDate;
    }

    public void setCreditcardExpDate(String creditcardExpDate) {
        this.creditcardExpDate = creditcardExpDate;
    }


    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    public LoginBean getSession() {
        return session;
    }

    public void setSession(LoginBean session) {
        this.session = session;
    }

    @Transactional
    public String bookingCreditcard() throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {
        if (!PaymentValidator.creditcardValidation()) {
            return "pretty:view-index";
        } else {
            System.err.println("Booked via creditcard!!!");

            for (Ticket ticket : session.getBooking().getTickets()) {
                session.getBooking().setOrderPrice(session.getBooking().getOrderPrice() + ticket.getSalesPrice());
            }
            session.getBooking().setOrderPrice(session.getBooking().getOrderPrice()*RDAIR_CREDITCARD_PROMO);
            session.getBooking().setStatus("accepted");
            ut.begin();
            or.save(session.getBooking());
            ut.commit();

            MailService ms = new MailService();
            ms.mail("frederik.vanherbruggen@realdolmen.com", "Your order at rdAir", "Thank you for your order!");

            isBooked = true;
            return "pretty:view-bookingcreditcard";
        }
    }
}