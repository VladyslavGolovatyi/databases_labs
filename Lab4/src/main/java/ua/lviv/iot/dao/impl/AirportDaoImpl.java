package ua.lviv.iot.dao.impl;

import ua.lviv.iot.dao.AirportDao;
import ua.lviv.iot.domain.Airport;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("SqlResolve")
@Service
public class AirportDaoImpl implements AirportDao {

    private static final String FIND_ALL = "SELECT * FROM airports";
    private static final String CREATE = "INSERT airports(name) VALUES (?)";
    private static final String UPDATE = "UPDATE airports SET name=? WHERE id=?";
    private static final String DELETE = "DELETE FROM airports WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM airports WHERE id=?";
    private static final String FIND_BY_AIRPORT_NAME = "SELECT * FROM airports WHERE name=?";

    private final JdbcTemplate jdbcTemplate;

    public AirportDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Optional<Airport> findByAirportName(String name) {
        Optional<Airport> airport;
        try {
            airport = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_AIRPORT_NAME,
                    BeanPropertyRowMapper.newInstance(Airport.class), name));
        } catch (EmptyResultDataAccessException e) {
            airport = Optional.empty();
        }
        return airport;
    }

    @Override
    public List<Airport> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Airport.class));
    }

    @Override
    public Optional<Airport> findById(Integer id) {
        Optional<Airport> airport;
        try {
            airport = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Airport.class), id));
        } catch (EmptyResultDataAccessException e) {
            airport = Optional.empty();
        }
        return airport;
    }

    @Override
    public int create(Airport airport) {
        return jdbcTemplate.update(CREATE, airport.getName());
    }

    @Override
    public int update(Integer id, Airport airport) {
        return jdbcTemplate.update(UPDATE, airport.getName(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }
}
