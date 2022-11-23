package ua.lviv.iot.service.impl;

import ua.lviv.iot.domain.Plane;
import ua.lviv.iot.exception.PlaneNotFoundException;
import ua.lviv.iot.repository.UserRepository;
import ua.lviv.iot.repository.PlaneRepository;
import ua.lviv.iot.service.PlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaneServiceImpl implements PlaneService {

    @Autowired
    PlaneRepository planeRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<Plane> findAll() {
        return planeRepository.findAll();
    }

    @Override
    public Plane findById(Integer id) {
        return planeRepository.findById(id)
                .orElseThrow(() -> new PlaneNotFoundException(id));
    }

    @Override
    public Plane create(Plane planes) {
        planeRepository.save(planes);
        return planes;
    }

    @Override
    public void update(Integer id, Plane uPlanes) {
        Plane planes = planeRepository.findById(id)
                .orElseThrow(() -> new PlaneNotFoundException(id));
        //update
        planes.setMaxPassengers(uPlanes.getMaxPassengers());
        planes.setAirline(uPlanes.getAirline());
        planeRepository.save(planes);
    }

    @Override
    public void delete(Integer id) {
        Plane planes = planeRepository.findById(id)
                .orElseThrow(() -> new PlaneNotFoundException(id));
        planeRepository.delete(planes);
    }
}
