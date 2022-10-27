package ua.lviv.iot.dao;

import ua.lviv.iot.domain.Plane;
import ua.lviv.iot.domain.Airline;

import java.util.List;
import java.util.Optional;

public interface AirlineDao extends GeneralDao<Airline, Integer>{
    Optional<Airline> findByAirlineName(String name);

    List<Plane> findAllPlanesBy(Integer id);
}
