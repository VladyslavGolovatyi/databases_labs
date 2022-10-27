package ua.lviv.iot.service.impl;

import ua.lviv.iot.dao.UserDao;
import ua.lviv.iot.domain.User;
import ua.lviv.iot.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Optional<User> findByUserName(String name) {
        return userDao.findByUserName(name);
    }

    @Override
    public Optional<User> findByUserEmail(String email) {
        return userDao.findByUserEmail(email);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public Optional<User> findById(Integer id) {
        return userDao.findById(id);
    }

    @Override
    public int create(User user) {
        return userDao.create(user);
    }

    @Override
    public int update(Integer id, User user) {
        return userDao.update(id, user);
    }

    @Override
    public int delete(Integer id) {
        return userDao.delete(id);
    }
}
