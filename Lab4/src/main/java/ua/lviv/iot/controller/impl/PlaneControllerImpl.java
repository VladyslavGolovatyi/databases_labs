package ua.lviv.iot.controller.impl;

import ua.lviv.iot.controller.PlaneController;
import ua.lviv.iot.domain.Plane;
import ua.lviv.iot.service.PlanesService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaneControllerImpl implements PlaneController {

    private final PlanesService planesService;

    public PlaneControllerImpl(PlanesService planesService) {
        this.planesService = planesService;
    }

    @Override
    public List<Plane> findAll() {
        return planesService.findAll();
    }

    @Override
    public Optional<Plane> findById(Integer id) {
        return planesService.findById(id);
    }

    @Override
    public int create(Plane plane) {
        return planesService.create(plane);
    }

    @Override
    public int update(Integer id, Plane plane) {
        return planesService.update(id, plane);
    }

    @Override
    public int delete(Integer id) {
        return planesService.delete(id);
    }
}
