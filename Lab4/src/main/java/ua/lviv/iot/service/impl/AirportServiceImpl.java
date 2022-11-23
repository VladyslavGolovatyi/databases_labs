package ua.lviv.iot.service.impl;

import ua.lviv.iot.dao.AirportDao;
import ua.lviv.iot.domain.Airport;
import ua.lviv.iot.service.AirportService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportServiceImpl implements AirportService {

    private final AirportDao airportDao;

    public AirportServiceImpl(AirportDao airportDao) {
        this.airportDao = airportDao;
    }

    @Override
    public Optional<Airport> findByAirportName(String name) {
        return airportDao.findByAirportName(name);
    }

    @Override
    public List<Airport> findAll() {
        return airportDao.findAll();
    }

    @Override
    public Optional<Airport> findById(Integer id) {
        return airportDao.findById(id);
    }

    @Override
    public int create(Airport airport) {
        return airportDao.create(airport);
    }

    @Override
    public int update(Integer id, Airport airport) {
        return airportDao.update(id, airport);
    }

    @Override
    public int delete(Integer id) {
        return airportDao.delete(id);
    }
}
