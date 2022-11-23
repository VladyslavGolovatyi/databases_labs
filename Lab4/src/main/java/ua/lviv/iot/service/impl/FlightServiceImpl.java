package ua.lviv.iot.service.impl;

import ua.lviv.iot.dao.FlightDao;
import ua.lviv.iot.domain.Flight;
import ua.lviv.iot.service.FlightService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightServiceImpl implements FlightService {
    private final FlightDao flightDao;

    public FlightServiceImpl(FlightDao flightDao) {
        this.flightDao = flightDao;
    }

    @Override
    public List<Flight> findAll() {
        return flightDao.findAll();
    }

    @Override
    public Optional<Flight> findById(Integer id) {
        return flightDao.findById(id);
    }

    @Override
    public int create(Flight flight) {
        return flightDao.create(flight);
    }

    @Override
    public int update(Integer id, Flight flight) {
        return flightDao.update(id, flight);
    }

    @Override
    public int delete(Integer id) {
        return flightDao.delete(id);
    }

}
