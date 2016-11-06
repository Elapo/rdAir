package com.realdolmen.rdAir.domain;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Frederik Van Herbruggen on 4/11/2016.
 */
@Entity
public class RDEmployee extends User {
    @ElementCollection
    private List<String> priceWarnings;

    public RDEmployee() {
        super();
        priceWarnings = new ArrayList<>();
    }

    public RDEmployee(String firstName, String lastName, String address, String telephone, String email, List<String> priceWarnings) {
        super(firstName, lastName, address, telephone, email);
        priceWarnings = new ArrayList<>();
        this.priceWarnings = priceWarnings;
    }

    public List<String> getPriceWarnings() {
        return priceWarnings;
    }
}
