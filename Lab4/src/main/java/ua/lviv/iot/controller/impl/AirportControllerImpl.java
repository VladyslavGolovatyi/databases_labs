package ua.lviv.iot.controller.impl;

import ua.lviv.iot.controller.AirportController;
import ua.lviv.iot.domain.Airport;
import ua.lviv.iot.service.AirportService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportControllerImpl implements AirportController {
    private final AirportService airportService;

    public AirportControllerImpl(AirportService airportService) {
        this.airportService = airportService;
    }

    @Override
    public Optional<Airport> findByAirportName(String name) {
        return airportService.findByAirportName(name);
    }

    @Override
    public List<Airport> findAll() {
        return airportService.findAll();
    }

    @Override
    public Optional<Airport> findById(Integer id) {
        return airportService.findById(id);
    }

    @Override
    public int create(Airport airport) {
        return airportService.create(airport);
    }

    @Override
    public int update(Integer id, Airport airport) {
        return airportService.update(id, airport);
    }

    @Override
    public int delete(Integer id) {
        return airportService.delete(id);
    }
}
