package com.realdolmen.rdAir.controllers.employeeBeans;

import com.realdolmen.rdAir.domain.Airline;
import com.realdolmen.rdAir.repositories.UserRepository;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.util.List;

@ViewScoped
public class PartnerListBean {
    @Inject
    UserRepository ur;

    List<Airline> partners;

    public String getPartners(int page, int perpage){
        partners = ur.getPerPage(page, perpage, Airline.class);
        return "pretty:";
    }

    public List<Airline> getPartners() {
        return partners;
    }
}
