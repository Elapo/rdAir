package com.realdolmen.rdAir.domain;

import javax.persistence.Entity;
import java.util.List;

/**
 * Created by Frederik Van Herbruggen on 4/11/2016.
 */
@Entity
public class RDEmployee extends User {
    private List<String> PriceWarnings;

    public RDEmployee() {
        super();
    }

    public RDEmployee(String firstName, String lastName, String address, String telephone, String email, List<String> priceWarnings) {
        super(firstName, lastName, address, telephone, email);
        PriceWarnings = priceWarnings;
    }

    public List<String> getPriceWarnings() {
        return PriceWarnings;
    }
}
