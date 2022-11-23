package ua.lviv.iot.service.impl;


import ua.lviv.iot.domain.User;
import ua.lviv.iot.exception.UserNotFoundException;
import ua.lviv.iot.repository.UserRepository;
import ua.lviv.iot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Transactional
    public User create(User user) {
        userRepository.save(user);
        return user;
    }

    @Transactional
    public void update(Integer id, User uUser) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        //update
        user.setFullName(uUser.getFullName());
        user.setEmail(uUser.getEmail());
        user.setPassword(uUser.getPassword());
        user.setFlights(uUser.getFlights());

        userRepository.save(user);
    }

    @Transactional
    public void delete(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        userRepository.delete(user);
    }
}
