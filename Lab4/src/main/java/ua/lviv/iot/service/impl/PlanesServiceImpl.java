package ua.lviv.iot.service.impl;

import ua.lviv.iot.dao.PlanesDao;
import ua.lviv.iot.domain.Plane;
import ua.lviv.iot.service.PlanesService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanesServiceImpl implements PlanesService {

    private final PlanesDao planesDao;

    public PlanesServiceImpl(PlanesDao planesDao) {
        this.planesDao = planesDao;
    }

    @Override
    public List<Plane> findAll() {
        return planesDao.findAll();
    }

    @Override
    public Optional<Plane> findById(Integer id) {
        return planesDao.findById(id);
    }

    @Override
    public int create(Plane plane) {
        return planesDao.create(plane);
    }

    @Override
    public int update(Integer id, Plane plane) {
        return planesDao.update(id, plane);
    }

    @Override
    public int delete(Integer id) {
        return planesDao.delete(id);
    }
}
