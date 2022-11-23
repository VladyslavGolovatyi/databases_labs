package ua.lviv.iot.service.impl;

import ua.lviv.iot.domain.Plane;
import ua.lviv.iot.exception.PlaneNotFoundException;
import ua.lviv.iot.repository.UserRepository;
import ua.lviv.iot.repository.PlanesRepository;
import ua.lviv.iot.service.PlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaneServiceImpl implements PlaneService {

    @Autowired
    PlanesRepository planesRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<Plane> findAll() {
        return planesRepository.findAll();
    }

    @Override
    public Plane findById(Integer id) {
        return planesRepository.findById(id)
                .orElseThrow(() -> new PlaneNotFoundException(id));
    }

    @Override
    public Plane create(Plane planes) {
        planesRepository.save(planes);
        return planes;
    }

    @Override
    public void update(Integer id, Plane uPlanes) {
        Plane planes = planesRepository.findById(id)
                .orElseThrow(() -> new PlaneNotFoundException(id));
        //update
        planes.setMaxPassengers(uPlanes.getMaxPassengers());
        planes.setAirline(uPlanes.getAirline());
        planesRepository.save(planes);
    }

    @Override
    public void delete(Integer id) {
        Plane planes = planesRepository.findById(id)
                .orElseThrow(() -> new PlaneNotFoundException(id));
        planesRepository.delete(planes);
    }
}
