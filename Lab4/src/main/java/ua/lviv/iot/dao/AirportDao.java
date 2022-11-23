package ua.lviv.iot.dao;
import ua.lviv.iot.domain.Airport;

import java.util.Optional;

public interface AirportDao extends GeneralDao<Airport, Integer> {
    Optional<Airport> findByAirportName(String name);
}
