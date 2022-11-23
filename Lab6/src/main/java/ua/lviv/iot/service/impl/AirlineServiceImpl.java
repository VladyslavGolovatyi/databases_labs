package ua.lviv.iot.service.impl;

import ua.lviv.iot.domain.Airport;
import ua.lviv.iot.domain.Plane;
import ua.lviv.iot.domain.Airline;
import ua.lviv.iot.exception.AirlineNotFoundException;
import ua.lviv.iot.repository.AirlineRepository;
import ua.lviv.iot.service.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirlineServiceImpl implements AirlineService {

    @Autowired
    AirlineRepository airlineRepository;

    @Override
    public List<Plane> findAllPlanesByAirlineId(Integer airlineId) {
        Airline airline = airlineRepository.findById(airlineId)
                .orElseThrow(() -> new AirlineNotFoundException(airlineId));
        return airline.getPlanes().stream().toList();
    }

    @Override
    public List<Airport> findBaseAirportsByAirlineId(Integer airlineId) {
        Airline airline = airlineRepository.findById(airlineId)
                .orElseThrow(() -> new AirlineNotFoundException(airlineId));
        return airline.getBaseAirports().stream().toList();
    }

    @Override
    public List<Airline> findAll() {
        return airlineRepository.findAll();
    }

    @Override
    public Airline findById(Integer id) {
        return airlineRepository.findById(id)
                .orElseThrow(() -> new AirlineNotFoundException(id));
    }

    @Override
    public Airline create(Airline airline) {
        airlineRepository.save(airline);
        return airline;
    }

    @Override
    public void update(Integer id, Airline uAirline) {
        Airline airline = airlineRepository.findById(id)
                .orElseThrow(() -> new AirlineNotFoundException(id));
        airline.setName(uAirline.getName());
        airline.setFoundationYear(uAirline.getFoundationYear());
        airlineRepository.save(airline);
    }

    @Override
    public void delete(Integer id) {
        Airline airline = airlineRepository.findById(id)
                .orElseThrow(() -> new AirlineNotFoundException(id));
        airlineRepository.delete(airline);
    }
}
