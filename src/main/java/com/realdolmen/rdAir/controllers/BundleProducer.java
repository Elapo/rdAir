package com.realdolmen.rdAir.controllers;

/**
 * Created by HBSBB70 on 12/11/2016.
 */
import java.util.PropertyResourceBundle;
import javax.enterprise.inject.Produces;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@SessionScoped
public class BundleProducer {

    @Produces
    public PropertyResourceBundle getBundle() {
        FacesContext context = FacesContext.getCurrentInstance();
        return context.getApplication().evaluateExpressionGet(context, "#{msgs}", PropertyResourceBundle.class);
    }

}