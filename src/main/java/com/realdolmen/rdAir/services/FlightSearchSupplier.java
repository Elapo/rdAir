package com.realdolmen.rdAir.services;

import com.realdolmen.rdAir.domain.Flight;
import com.realdolmen.rdAir.domain.Ticket;

import java.util.Date;
import java.util.List;

public interface FlightSearchSupplier {
    public List<Flight> searchFlights(int seats, String fClass, String airComp, String dep, String dest, String Region, Date departureDate);
}
