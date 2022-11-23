package ua.lviv.iot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.domain.Airline;
import ua.lviv.iot.domain.Airport;
import ua.lviv.iot.domain.Plane;
import ua.lviv.iot.dto.AirlineDto;
import ua.lviv.iot.dto.AirportDto;
import ua.lviv.iot.dto.PlaneDto;
import ua.lviv.iot.dto.assembler.AirlineDtoAssembler;
import ua.lviv.iot.dto.assembler.AirportDtoAssembler;
import ua.lviv.iot.dto.assembler.PlaneDtoAssembler;
import ua.lviv.iot.service.AirlineService;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api/airlines")
public class AirlineController {
    @Autowired
    private AirlineService airlineService;
    @Autowired
    private AirlineDtoAssembler airlineDtoAssembler;

    @Autowired
    private AirportDtoAssembler airportDtoAssembler;

    @Autowired
    private PlaneDtoAssembler planeDtoAssembler;

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<AirlineDto>> getAllAirlines() {
        List<Airline> airlines = airlineService.findAll();
        CollectionModel<AirlineDto> airlineDtos = airlineDtoAssembler.toCollectionModel(airlines);
        return new ResponseEntity<>(airlineDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/{airlineId}")
    public ResponseEntity<AirlineDto> getAirline(@PathVariable Integer airlineId) {
        Airline airline = airlineService.findById(airlineId);
        AirlineDto airlineDto = airlineDtoAssembler.toModel(airline);
        return new ResponseEntity<>(airlineDto, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<AirlineDto> addAirline(@RequestBody Airline airline) {
        Airline newAirline = airlineService.create(airline);
        AirlineDto airlineDto = airlineDtoAssembler.toModel(newAirline);
        return new ResponseEntity<>(airlineDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{airlineId}")
    public ResponseEntity<?> updateAirline(@RequestBody Airline uAirline, @PathVariable Integer airlineId) {
        airlineService.update(airlineId, uAirline);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{airlineId}")
    public ResponseEntity<?> deleteAirline(@PathVariable Integer airlineId) {
        airlineService.delete(airlineId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{airlineId}/planes")
    public ResponseEntity<CollectionModel<PlaneDto>> getAllPlanesByAirlineId(@PathVariable Integer airlineId) {
        List<Plane> planes = airlineService.findAllPlanesByAirlineId(airlineId);
        Link selfLink = linkTo(methodOn(AirlineController.class).getAllPlanesByAirlineId(airlineId)).withSelfRel();
        CollectionModel<PlaneDto> planesDtos = planeDtoAssembler.toCollectionModel(planes, selfLink);
        return new ResponseEntity<>(planesDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/{airlineId}/airports")
    public ResponseEntity<CollectionModel<AirportDto>> getAllAirportsByAirlineId(@PathVariable Integer airlineId) {
        List<Airport> baseAirports = airlineService.findBaseAirportsByAirlineId(airlineId);
        Link selfLink = linkTo(methodOn(AirlineController.class).getAllAirportsByAirlineId(airlineId)).withSelfRel();
        CollectionModel<AirportDto> airportsDtos = airportDtoAssembler.toCollectionModel(baseAirports, selfLink);
        return new ResponseEntity<>(airportsDtos, HttpStatus.OK);
    }
}
