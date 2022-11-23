package ua.lviv.iot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.repository.PlaneRepository;
import ua.lviv.iot.service.PlaneProcedureService;

@Service
public class PlaneProcedureServiceImpl implements PlaneProcedureService {
    @Autowired
    PlaneRepository planeRepository;

    @Override
    public int getMaxPassengers() {
        return planeRepository.getMaxPassengers();
    }
}
