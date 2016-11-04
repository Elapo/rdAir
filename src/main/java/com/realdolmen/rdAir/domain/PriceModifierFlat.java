package com.realdolmen.rdAir.domain;

import javax.persistence.Entity;
import java.util.Date;

/**
 * Created by Frederik Van Herbruggen on 4/11/2016.
 */
@Entity
public class PriceModifierFlat extends PriceModifier {
    private double amount;

    public PriceModifierFlat() {
        super();
    }

    public PriceModifierFlat(String name, Date startDate, Date endDate, Date startTime, Date endTime, double amount) {
        super(name, startDate, endDate, startTime, endTime);
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
