package com.realdolmen.rdAir.controllers.employeeBeans;

import com.realdolmen.rdAir.domain.Airline;
import com.realdolmen.rdAir.repositories.UserRepository;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.util.List;

@ManagedBean
@ViewScoped
public class PartnerListBean {
    @Inject
    UserRepository ur;

    private List<Airline> partners;

    private Long partnerCount;

    @PostConstruct
    public void init(){
        partnerCount = ur.getCount(Airline.class);
        partners = ur.getPerPage(1, partnerCount.intValue(), Airline.class);
    }

    public long getPartnerCount(){
        return partnerCount;
    }
    public List<Airline> getPartners() {
        return partners;
    }
}
