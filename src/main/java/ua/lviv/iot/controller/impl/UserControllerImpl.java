package ua.lviv.iot.controller.impl;

import ua.lviv.iot.controller.UserController;
import ua.lviv.iot.domain.User;
import ua.lviv.iot.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserControllerImpl implements UserController {
    private final UserService userService;

    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Optional<User> findByUserName(String name) {
        return userService.findByUserName(name);
    }

    @Override
    public Optional<User> findByUserEmail(String email) {
        return userService.findByUserEmail(email);
    }

    @Override
    public List<User> findAll() {
        return userService.findAll();
    }

    @Override
    public Optional<User> findById(Integer id) {
        return userService.findById(id);
    }

    @Override
    public int create(User user) {
        return userService.create(user);
    }

    @Override
    public int update(Integer id, User user) {
        return userService.update(id, user);
    }

    @Override
    public int delete(Integer id) {
        return userService.delete(id);
    }
}
