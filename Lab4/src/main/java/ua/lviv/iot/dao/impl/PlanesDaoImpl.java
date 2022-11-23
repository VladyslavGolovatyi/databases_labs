package ua.lviv.iot.dao.impl;

import ua.lviv.iot.dao.PlanesDao;
import ua.lviv.iot.domain.Plane;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("SqlResolve")
@Service
public class PlanesDaoImpl implements PlanesDao {

    private static final String FIND_ALL = "SELECT * FROM planes";
    private static final String CREATE = "INSERT planes(airline_id, max_passengers) VALUES (?,?)";
    private static final String UPDATE = "UPDATE planes SET airline_id=?, max_passengers=? WHERE id=?";
    private static final String DELETE = "DELETE FROM planes WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM planes WHERE id=?";

    private final JdbcTemplate jdbcTemplate;

    public PlanesDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Plane> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Plane.class));
    }

    @Override
    public Optional<Plane> findById(Integer id) {
        Optional<Plane> planes;
        try {
            planes = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Plane.class), id));
        } catch (EmptyResultDataAccessException e) {
            planes = Optional.empty();
        }
        return planes;
    }

    @Override
    public int create(Plane plane) {
        return jdbcTemplate.update(CREATE, plane.getAirlineId(), plane.getMaxPassengers());
    }

    @Override
    public int update(Integer id, Plane plane) {
        return jdbcTemplate.update(UPDATE, plane.getAirlineId(), plane.getMaxPassengers(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }
}
