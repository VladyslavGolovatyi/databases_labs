package ua.lviv.iot.controller;


import ua.lviv.iot.domain.User;

import java.util.Optional;

public interface UserController extends GeneralController<User, Integer>{
    Optional<User> findByUserName(String name);

    Optional<User> findByUserEmail(String email);
}
