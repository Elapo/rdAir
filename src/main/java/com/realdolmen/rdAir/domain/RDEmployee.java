package com.realdolmen.rdAir.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Frederik Van Herbruggen on 4/11/2016.
 */
@Entity
public class RDEmployee extends User implements Serializable{
    @ElementCollection
    private List<String> priceWarnings;

    public RDEmployee() {
        super();
        priceWarnings = new ArrayList<>();
    }

    public RDEmployee(String firstName, String lastName, String address, String telephone, String email,String passwordHash, List<String> priceWarnings) {
        super(firstName, lastName, address, telephone, email, passwordHash);
        priceWarnings = new ArrayList<>();
        this.priceWarnings = priceWarnings;
    }

    public List<String> getPriceWarnings() {
        return priceWarnings;
    }
}
