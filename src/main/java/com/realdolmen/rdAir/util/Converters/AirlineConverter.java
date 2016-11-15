package com.realdolmen.rdAir.util.Converters;

import com.realdolmen.rdAir.controllers.SearchFlightBean;
import com.realdolmen.rdAir.domain.Airline;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by HBSBB70 on 15/11/2016.
 */

@Named
public class AirlineConverter implements Converter {


    @Inject
    SearchFlightBean sfb;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return sfb.getUserRepository().getAirlineByName(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((Airline) value).getAirlineName();
    }
}
