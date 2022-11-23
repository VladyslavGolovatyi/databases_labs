package ua.lviv.iot.service;

import ua.lviv.iot.domain.Airport;
import ua.lviv.iot.domain.Plane;
import ua.lviv.iot.domain.Airline;

import java.util.List;

public interface AirlineService extends GeneralService<Airline, Integer>{
    List<Plane> findAllPlanesByAirlineId(Integer id);
    List<Airport> findBaseAirportsByAirlineId(Integer id);
}
