package ua.lviv.iot.controller;

import ua.lviv.iot.domain.Airport;

import java.util.Optional;

public interface AirportController extends GeneralController<Airport, Integer>{
    Optional<Airport> findByAirportName(String name);
}
