package com.realdolmen.rdAir.controllers;

import com.realdolmen.rdAir.util.PaymentValidator;
import org.hibernate.validator.constraints.NotEmpty;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.validation.constraints.*;
import java.io.Serializable;

@ManagedBean
@SessionScoped
public class bookingBean implements Serializable {

    private boolean isBooked;

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

    public String bookingCreditcard(){
        if (!PaymentValidator.creditcardValidation()) {
            return "pretty:view-index";
        } else {
            System.err.println("Booked via creditcard!!!");
            isBooked = true;
            return "pretty:view-bookingcreditcard";
        }
    }
}
