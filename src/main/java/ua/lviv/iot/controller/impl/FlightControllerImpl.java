package ua.lviv.iot.controller.impl;

import ua.lviv.iot.controller.FlightController;
import ua.lviv.iot.domain.Flight;
import ua.lviv.iot.service.FlightService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightControllerImpl implements FlightController {

    private final FlightService flightService;

    public FlightControllerImpl(FlightService flightService) {
        this.flightService = flightService;
    }

    @Override
    public List<Flight> findAll() {
        return flightService.findAll();
    }

    @Override
    public Optional<Flight> findById(Integer id) {
        return flightService.findById(id);
    }

    @Override
    public int create(Flight flight) {
        return flightService.create(flight);
    }

    @Override
    public int update(Integer id, Flight flight) {
        return flightService.update(id, flight);
    }

    @Override
    public int delete(Integer id) {
        return flightService.delete(id);
    }

}
