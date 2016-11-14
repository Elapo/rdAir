package com.realdolmen.rdAir.controllers.employeeBeans;

import com.realdolmen.rdAir.domain.Location;
import com.realdolmen.rdAir.domain.Region;
import com.realdolmen.rdAir.repositories.LocationRepository;
import com.realdolmen.rdAir.repositories.RegionRepository;
import org.hibernate.Hibernate;
import org.hibernate.annotations.SourceType;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@ViewScoped
public class LocationListBean  implements Serializable{

    @Inject
    LocationRepository lr;

    @Inject
    RegionRepository rr;

    private List<Location> locations;

    private List<String> regions;

    private String name, code, region;

    private Location selected;
    @PostConstruct
    public void init(){
        locations = lr.getAllLocations();
        regions = rr.getAllRegionNames();
    }

    public void addLocation(){
        lr.save(new Location(name, code, rr.getRegionByName(region)));
        locations = lr.getAllLocations();
    }

    public void deleteLocation(){
        lr.delete(selected.getId());
        locations = lr.getAllLocations();
    }

    public void editLocation(RowEditEvent e){
        Object old = e.getObject();
        Location l = (Location) old;
        System.out.println(l.getAirportCode());
    }
    public void onEditCancel(RowEditEvent event) {}

    public List<Location> getLocations() {
        return locations;
    }

    public List<String> getRegions() {
        return regions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Location getSelected() {
        return selected;
    }

    public void setSelected(Location selected) {
        this.selected = selected;
    }
}
