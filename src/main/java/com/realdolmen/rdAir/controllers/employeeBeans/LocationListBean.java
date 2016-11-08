package com.realdolmen.rdAir.controllers.employeeBeans;

import com.realdolmen.rdAir.domain.Location;
import com.realdolmen.rdAir.repositories.LocationRepository;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.util.List;

@ViewScoped
public class LocationListBean {

    @Inject
    LocationRepository lr;

    private List<Location> locations;

    public String getLocations(int page, int perpage){
        locations = lr.getPerPage(page, perpage);
        return "pretty:";
    }

    public List<Location> getLocations() {
        return locations;
    }
}
