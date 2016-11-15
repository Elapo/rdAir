package com.realdolmen.rdAir.util.Converters;

import com.realdolmen.rdAir.controllers.SearchFlightBean;
import com.realdolmen.rdAir.domain.Airline;
import com.realdolmen.rdAir.domain.Location;
import sun.reflect.annotation.TypeAnnotation;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by HBSBB70 on 15/11/2016.
 */

@Named
public class LocationConverter implements Converter {

    @Inject
    SearchFlightBean sfb;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return sfb.getLocationRepository().findByName(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Location l = (Location) value;
        String ret = l.getAirportCode() + ": " + l.getAirportName() + "("+l.getRegion().getName()+")";
        return ret;
    }
}
