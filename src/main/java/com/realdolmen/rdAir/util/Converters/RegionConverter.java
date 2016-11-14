package com.realdolmen.rdAir.util.Converters;

import com.realdolmen.rdAir.controllers.employeeBeans.LocationListBean;
import com.realdolmen.rdAir.domain.Region;
import com.realdolmen.rdAir.repositories.RegionRepository;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class RegionConverter implements Converter {

    @Inject
    LocationListBean llb;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return llb.getRr().getRegionByName(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((Region)value).getName();
    }
}
