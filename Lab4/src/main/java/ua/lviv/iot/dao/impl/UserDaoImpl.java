package ua.lviv.iot.dao.impl;

import ua.lviv.iot.dao.UserDao;
import ua.lviv.iot.domain.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("SqlResolve")
@Service
public class UserDaoImpl implements UserDao {

    private static final String FIND_ALL = "SELECT * FROM users";
    private static final String CREATE = "INSERT users(name, full_name, email, password) VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE users SET name=?, full_name=?, email=?, password=? WHERE id=?";
    private static final String DELETE = "DELETE FROM users WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM users WHERE id=?";
    private static final String FIND_BY_CUSTOMER_NAME = "SELECT * FROM users WHERE name=?";
    private static final String FIND_BY_CUSTOMER_EMAIL = "SELECT * FROM users WHERE email=?";

    private final JdbcTemplate jdbcTemplate;

    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<User> findByUserName(String name) {
        Optional<User> user;
        try {
            user = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_CUSTOMER_NAME,
                    BeanPropertyRowMapper.newInstance(User.class), name));
        } catch (EmptyResultDataAccessException e) {
            user = Optional.empty();
        }
        return user;
    }

    @Override
    public Optional<User> findByUserEmail(String email) {
        Optional<User> user;
        try {
            user = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_CUSTOMER_EMAIL,
                    BeanPropertyRowMapper.newInstance(User.class), email));
        } catch (EmptyResultDataAccessException e) {
            user = Optional.empty();
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(User.class));
    }

    @Override
    public Optional<User> findById(Integer id) {
        Optional<User> user;
        try {
            user = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(User.class), id));
        } catch (EmptyResultDataAccessException e) {
            user = Optional.empty();
        }
        return user;
    }

    @Override
    public int create(User user) {
        return jdbcTemplate.update(CREATE, user.getFullName(), user.getEmail(), user.getPassword());
    }

    @Override
    public int update(Integer id, User user) {
        return jdbcTemplate.update(UPDATE, user.getFullName(), user.getEmail(), user.getPassword(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }
}
