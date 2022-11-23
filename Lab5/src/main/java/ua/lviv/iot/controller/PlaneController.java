package ua.lviv.iot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.domain.Plane;
import ua.lviv.iot.dto.PlaneDto;
import ua.lviv.iot.dto.assembler.PlaneDtoAssembler;
import ua.lviv.iot.service.PlaneService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/planes")
public class PlaneController {
    @Autowired
    private PlaneService planeService;
    @Autowired
    private PlaneDtoAssembler planeDtoAssembler;

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<PlaneDto>> getAllPlanes() {
        List<Plane> planes = planeService.findAll();
        CollectionModel<PlaneDto> planeDtos = planeDtoAssembler.toCollectionModel(planes);
        return new ResponseEntity<>(planeDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/{planeId}")
    public ResponseEntity<PlaneDto> getPlane(@PathVariable Integer planeId) {
        Plane plane = planeService.findById(planeId);
        PlaneDto planeDto = planeDtoAssembler.toModel(plane);
        return new ResponseEntity<>(planeDto, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<PlaneDto> addPlane(@RequestBody Plane plane) {
        Plane newPlane = planeService.create(plane);
        PlaneDto planeDto = planeDtoAssembler.toModel(newPlane);
        return new ResponseEntity<>(planeDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{planeId}")
    public ResponseEntity<?> updatePlane(@RequestBody Plane uPlane, @PathVariable Integer planeId) {
        planeService.update(planeId, uPlane);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{planeId}")
    public ResponseEntity<?> deletePlane(@PathVariable Integer planeId) {
        planeService.delete(planeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
