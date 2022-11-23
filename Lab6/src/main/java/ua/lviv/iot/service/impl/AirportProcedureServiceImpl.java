package ua.lviv.iot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.repository.AirportRepository;
import ua.lviv.iot.service.AirportProcedureService;

@Service
public class AirportProcedureServiceImpl implements AirportProcedureService {
    @Autowired
    AirportRepository airportRepository;

    @Override
    public void addTenAirports(String airportName) {
        airportRepository.addTenAirports(airportName);
    }

    @Override
    public void addAirport(String airportName) {
        airportRepository.addAirport(airportName);
    }

    @Override
    public void addBaseAirport(int airportId, int airlineId) {
        airportRepository.addBaseAirport(airportId, airlineId);
    }
}
