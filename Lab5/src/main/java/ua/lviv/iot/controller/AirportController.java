package ua.lviv.iot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.domain.Airport;
import ua.lviv.iot.dto.AirportDto;
import ua.lviv.iot.dto.assembler.AirportDtoAssembler;
import ua.lviv.iot.service.AirportService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/airports")
public class AirportController {
    @Autowired
    private AirportService airportService;
    @Autowired
    private AirportDtoAssembler airportDtoAssembler;

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<AirportDto>> getAllAirports() {
        List<Airport> airports = airportService.findAll();
        CollectionModel<AirportDto> airportDtos = airportDtoAssembler.toCollectionModel(airports);
        return new ResponseEntity<>(airportDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/{airportId}")
    public ResponseEntity<AirportDto> getAirport(@PathVariable Integer airportId) {
        Airport airport = airportService.findById(airportId);
        AirportDto airportDto = airportDtoAssembler.toModel(airport);
        return new ResponseEntity<>(airportDto, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<AirportDto> addAirport(@RequestBody Airport airport) {
        Airport newAirport = airportService.create(airport);
        AirportDto airportDto = airportDtoAssembler.toModel(newAirport);
        return new ResponseEntity<>(airportDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{airportId}")
    public ResponseEntity<?> updateAirport(@RequestBody Airport uAirport, @PathVariable Integer airportId) {
        airportService.update(airportId, uAirport);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{airportId}")
    public ResponseEntity<?> deleteAirport(@PathVariable Integer airportId) {
        airportService.delete(airportId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
