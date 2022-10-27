package ua.lviv.iot.service;

import ua.lviv.iot.domain.Plane;
import ua.lviv.iot.domain.Airline;

import java.util.List;
import java.util.Optional;

public interface AirlineService extends GeneralService<Airline, Integer>{

    Optional<Airline> findByAirlineName(String name);

    List<Plane> findAllPlanesBy(Integer id);
}
