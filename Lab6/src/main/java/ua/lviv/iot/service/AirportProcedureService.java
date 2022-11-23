package ua.lviv.iot.service;

public interface AirportProcedureService {
    void addTenAirports(String airportName);

    void addAirport(String airportName);

    void addBaseAirport(int airportId, int airlineId);
}
