package com.realdolmen.rdAir.controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import java.io.Serializable;
import java.util.*;


@ManagedBean
@SessionScoped
public class LanguageBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String localeCode;

    private static Map<String,Object> countries;
    static{
        countries = new LinkedHashMap<String,Object>();
        countries.put("English", Locale.ENGLISH); //label, value
        countries.put("French", Locale.FRENCH);
        Locale dutLocale = new Locale("nl", "DUTCH");
        countries.put("Dutch", dutLocale);

    }

    public Map<String, Object> getCountriesInMap() {
        return countries;
    }

    public List<String> getCountriesList(){
        List<String> list = new ArrayList<String>();
        for(Map.Entry<String,Object> entry : countries.entrySet()){
            list.add(entry.getKey());
        }
        System.err.println("LIJST MET TALEN");
        System.err.println(list);
        return list;
    }


    public String getLocaleCode() {
        return localeCode;
    }


    public void setLocaleCode(String localeCode) {
        this.localeCode = localeCode;
    }

    //value change event listener
    public void countryLocaleCodeChanged(ValueChangeEvent e){

        System.err.println("Inside method countryLocaleCodeChanged !!! ");

        String newLocaleValue = e.getNewValue().toString();

        //loop country map to compare the locale code
        for (Map.Entry<String, Object> entry : countries.entrySet()) {

            if(entry.getValue().toString().equals(newLocaleValue)){
                System.err.println("Inside method countryLocaleCodeChanged !!! Inside the " +
                        "if with newLocaleValue: " + newLocaleValue );
                FacesContext.getCurrentInstance()
                        .getViewRoot().setLocale((Locale)entry.getValue());

            }
        }
    }

}
