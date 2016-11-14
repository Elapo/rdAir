package com.realdolmen.rdAir.controllers.employeeBeans;

import com.realdolmen.rdAir.domain.Location;
import com.realdolmen.rdAir.domain.RDEmployee;
import com.realdolmen.rdAir.domain.Region;
import com.realdolmen.rdAir.repositories.LocationRepository;
import com.realdolmen.rdAir.repositories.RegionRepository;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;
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

    private List<Region> regObjs;

    private List<String> regions;

    private String name, code, region;

    private Location selected;
    @PostConstruct
    public void init(){
        locations = lr.getAllLocations();
        regions = rr.getAllRegionNames();
        regObjs = rr.getAllRegions();
        System.out.println("init done");
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
        Location toEdit = (Location) e.getObject();
        lr.save(toEdit);
        System.out.println("Edited location");
        locations = lr.getAllLocations();
    }

    public void editLocationRegion(ValueChangeEvent e){

    }

    public void onEditCancel(RowEditEvent event) {
        System.out.println("cancel edit");
    }

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

    public List<Region> getRegObjs() {
        return regObjs;
    }

    public void setRegObjs(List<Region> regObjs) {
        this.regObjs = regObjs;
    }

    public RegionRepository getRr() {
        return rr;
    }
}
