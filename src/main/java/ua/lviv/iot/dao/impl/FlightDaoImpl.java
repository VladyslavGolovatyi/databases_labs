package ua.lviv.iot.dao.impl;

import ua.lviv.iot.dao.FlightDao;
import ua.lviv.iot.domain.Flight;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("SqlResolve")
@Service
public class FlightDaoImpl implements FlightDao {

    private static final String FIND_ALL = "SELECT * FROM flights";
    private static final String CREATE = "INSERT flights(src_airport_id, dest_airport_id, plane_id) VALUES (?, ?, ?)";
    private static final String UPDATE = "UPDATE flights SET name=?, airport_id=? WHERE id=?";
    private static final String DELETE = "DELETE FROM flights WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM flights WHERE id=?";

    private final JdbcTemplate jdbcTemplate;

    public FlightDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Flight> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Flight.class));
    }

    @Override
    public Optional<Flight> findById(Integer id) {
        Optional<Flight> flight;
        try {
            flight = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Flight.class), id));
        } catch (EmptyResultDataAccessException e) {
            flight = Optional.empty();
        }
        return flight;
    }

    @Override
    public int create(Flight flight) {
        return jdbcTemplate.update(CREATE, flight.getSrcAirportId(), flight.getDestAirportId(), flight.getPlaneId());
    }

    @Override
    public int update(Integer id, Flight flight) {
        return jdbcTemplate.update(UPDATE, flight.getSrcAirportId(), flight.getDestAirportId(), flight.getPlaneId(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }

}
