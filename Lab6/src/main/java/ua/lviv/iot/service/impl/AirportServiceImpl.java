package ua.lviv.iot.service.impl;

import ua.lviv.iot.domain.Airport;
import ua.lviv.iot.exception.AirportNotFoundException;
import ua.lviv.iot.repository.AirportRepository;
import ua.lviv.iot.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AirportServiceImpl implements AirportService {

    @Autowired
    AirportRepository airportRepository;

    @Override
    public List<Airport> findAll() {
        return airportRepository.findAll();
    }

    @Override
    public Airport findById(Integer id) {
        return airportRepository.findById(id)
                .orElseThrow(() -> new AirportNotFoundException(id));
    }

    @Transactional
    public Airport create(Airport airport) {
        airportRepository.save(airport);
        return airport;
    }

    @Transactional
    public void update(Integer id, Airport uAirport) {
        Airport airport = airportRepository.findById(id)
                .orElseThrow(() -> new AirportNotFoundException(id));
        //update
        airport.setName(uAirport.getName());

        airportRepository.save(airport);
    }

    @Transactional
    public void delete(Integer id) {
        Airport airport = airportRepository.findById(id)
                .orElseThrow(() -> new AirportNotFoundException(id));
        airportRepository.delete(airport);
    }
}
