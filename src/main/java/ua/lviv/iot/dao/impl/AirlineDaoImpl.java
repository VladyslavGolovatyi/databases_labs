package ua.lviv.iot.dao.impl;

import ua.lviv.iot.dao.AirlineDao;
import ua.lviv.iot.domain.Plane;
import ua.lviv.iot.domain.Airline;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("SqlResolve")
@Service
public class AirlineDaoImpl implements AirlineDao {

    private static final String FIND_ALL = "SELECT * FROM airlines";
    private static final String CREATE = "INSERT airlines(name, foundation_year) VALUES (?, ?)";
    private static final String UPDATE = "UPDATE airlines SET name=?, foundation_year=? WHERE id=?";
    private static final String DELETE = "DELETE FROM airlines WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM airlines WHERE id=?";
    private static final String FIND_BY_AIRLINE_NAME = "SELECT * FROM airlines WHERE name=?";
    private static final String FIND_ALL_PLANES = "SELECT * FROM planes WHERE airline_id=?;";

    private final JdbcTemplate jdbcTemplate;

    public AirlineDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Airline> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Airline.class));
    }

    @Override
    public Optional<Airline> findById(Integer id) {
        Optional<Airline> airline;
        try {
            airline = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Airline.class), id));
        } catch (EmptyResultDataAccessException e) {
            airline = Optional.empty();
        }
        return airline;
    }

    @Override
    public int create(Airline airline) {
        return jdbcTemplate.update(CREATE, airline.getName(), airline.getFoundationYear());
    }

    @Override
    public int update(Integer id, Airline airline) {
        return jdbcTemplate.update(UPDATE, airline.getName(), airline.getFoundationYear(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }

    @Override
    public Optional<Airline> findByAirlineName(String name) {
        Optional<Airline> airline;
        try {
            airline = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_AIRLINE_NAME,
                    BeanPropertyRowMapper.newInstance(Airline.class), name));
        } catch (EmptyResultDataAccessException e) {
            airline = Optional.empty();
        }
        return airline;
    }

    @Override
    public List<Plane> findAllPlanesBy(Integer id) {
        return jdbcTemplate.query(FIND_ALL_PLANES, BeanPropertyRowMapper.newInstance(Plane.class), id);
    }
}
