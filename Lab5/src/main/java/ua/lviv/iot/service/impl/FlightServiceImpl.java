package ua.lviv.iot.service.impl;

import ua.lviv.iot.domain.Flight;
import ua.lviv.iot.exception.FlightNotFoundException;
import ua.lviv.iot.repository.FlightRepository;
import ua.lviv.iot.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    FlightRepository flightRepository;

    @Override
    public List<Flight> findAll() {
        return flightRepository.findAll();
    }

    @Override
    public Flight findById(Integer id) {
        return flightRepository.findById(id)
                .orElseThrow(() -> new FlightNotFoundException(id));
    }

    @Override
    public Flight create(Flight flight) {
        flightRepository.save(flight);
        return flight;
    }

    @Override
    public void update(Integer id, Flight uFlight) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new FlightNotFoundException(id));
        //update
        flight.setPassengers(uFlight.getPassengers());
        flightRepository.save(flight);
    }

    @Override
    public void delete(Integer id) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new FlightNotFoundException(id));
        flightRepository.delete(flight);
    }
}
