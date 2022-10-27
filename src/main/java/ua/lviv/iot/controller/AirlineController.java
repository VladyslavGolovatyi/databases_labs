package ua.lviv.iot.controller;

import ua.lviv.iot.domain.Plane;
import ua.lviv.iot.domain.Airline;

import java.util.List;
import java.util.Optional;

public interface AirlineController extends GeneralController<Airline, Integer>{
    Optional<Airline> findByAirlineName(String name);

    List<Plane> findAllPlanesBy(Integer id);
}
