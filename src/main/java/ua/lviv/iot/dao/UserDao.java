package ua.lviv.iot.dao;

import ua.lviv.iot.domain.User;

import java.util.Optional;

public interface UserDao extends GeneralDao<User, Integer> {
    Optional<User> findByUserName(String name);

    Optional<User> findByUserEmail(String email);
}
