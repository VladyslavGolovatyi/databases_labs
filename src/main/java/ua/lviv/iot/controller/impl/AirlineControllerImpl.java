package ua.lviv.iot.controller.impl;

import ua.lviv.iot.controller.AirlineController;
import ua.lviv.iot.domain.Plane;
import ua.lviv.iot.domain.Airline;
import ua.lviv.iot.service.AirlineService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirlineControllerImpl implements AirlineController {

    private final AirlineService airlineService;

    public AirlineControllerImpl(AirlineService airlineService) {
        this.airlineService = airlineService;
    }

    @Override
    public List<Airline> findAll() {
        return airlineService.findAll();
    }

    @Override
    public Optional<Airline> findById(Integer id) {
        return airlineService.findById(id);
    }

    @Override
    public int create(Airline airline) {
        return airlineService.create(airline);
    }

    @Override
    public int update(Integer id, Airline airline) {
        return airlineService.update(id, airline);
    }

    @Override
    public int delete(Integer id) {
        return airlineService.delete(id);
    }

    @Override
    public Optional<Airline> findByAirlineName(String name) {
        return airlineService.findByAirlineName(name);
    }

    @Override
    public List<Plane> findAllPlanesBy(Integer id) {
        return airlineService.findAllPlanesBy(id);
    }
}
