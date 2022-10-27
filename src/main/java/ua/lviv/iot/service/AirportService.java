package ua.lviv.iot.service;

import ua.lviv.iot.domain.Airport;

import java.util.Optional;

public interface AirportService extends GeneralService<Airport, Integer>{
    Optional<Airport> findByAirportName(String name);
}
