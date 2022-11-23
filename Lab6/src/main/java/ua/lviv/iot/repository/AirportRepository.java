package ua.lviv.iot.repository;

import org.springframework.data.jpa.repository.query.Procedure;
import ua.lviv.iot.domain.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Integer> {
    @Procedure("add_airport")
    void addAirport(String airportName);

    @Procedure("add_10_airports")
    void addTenAirports(String airportName);

    @Procedure("add_base_airport")
    void addBaseAirport(int airportId, int airlineId);
}
