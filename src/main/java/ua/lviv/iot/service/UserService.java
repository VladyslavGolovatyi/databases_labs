package ua.lviv.iot.service;

import ua.lviv.iot.domain.User;

import java.util.Optional;

public interface UserService extends GeneralService<User, Integer>{

    Optional<User> findByUserName(String name);

    Optional<User> findByUserEmail(String email);
}
