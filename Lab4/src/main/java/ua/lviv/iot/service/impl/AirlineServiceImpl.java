package ua.lviv.iot.service.impl;

import ua.lviv.iot.dao.AirlineDao;
import ua.lviv.iot.domain.Plane;
import ua.lviv.iot.domain.Airline;
import ua.lviv.iot.service.AirlineService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirlineServiceImpl implements AirlineService {
    private final AirlineDao airlineDao;

    public AirlineServiceImpl(AirlineDao airlineDao) {
        this.airlineDao = airlineDao;
    }

    @Override
    public List<Airline> findAll() {
        return airlineDao.findAll();
    }

    @Override
    public Optional<Airline> findById(Integer id) {
        return airlineDao.findById(id);
    }

    @Override
    public int create(Airline airline) {
        return airlineDao.create(airline);
    }

    @Override
    public int update(Integer id, Airline airline) {
        return airlineDao.update(id, airline);
    }

    @Override
    public int delete(Integer id) {
        return airlineDao.delete(id);
    }

    @Override
    public Optional<Airline> findByAirlineName(String name) {
        return airlineDao.findByAirlineName(name);
    }

    @Override
    public List<Plane> findAllPlanesBy(Integer id) {
        return airlineDao.findAllPlanesBy(id);
    }
}
