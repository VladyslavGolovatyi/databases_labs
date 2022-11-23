package ua.lviv.iot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.domain.Flight;
import ua.lviv.iot.dto.FlightDto;
import ua.lviv.iot.dto.assembler.FlightDtoAssembler;
import ua.lviv.iot.service.FlightService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/flights")
public class FlightController {
    @Autowired
    private FlightService flightService;
    @Autowired
    private FlightDtoAssembler flightDtoAssembler;

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<FlightDto>> getAllFlights() {
        List<Flight> flights = flightService.findAll();
        CollectionModel<FlightDto> flightDtos = flightDtoAssembler.toCollectionModel(flights);
        return new ResponseEntity<>(flightDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/{flightId}")
    public ResponseEntity<FlightDto> getFlight(@PathVariable Integer flightId) {
        Flight flight = flightService.findById(flightId);
        FlightDto flightDto = flightDtoAssembler.toModel(flight);
        return new ResponseEntity<>(flightDto, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<FlightDto> addFlight(@RequestBody Flight flight) {
        Flight newFlight = flightService.create(flight);
        FlightDto flightDto = flightDtoAssembler.toModel(newFlight);
        return new ResponseEntity<>(flightDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{flightId}")
    public ResponseEntity<?> updateFlight(@RequestBody Flight uFlight, @PathVariable Integer flightId) {
        flightService.update(flightId, uFlight);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{flightId}")
    public ResponseEntity<?> deleteFlight(@PathVariable Integer flightId) {
        flightService.delete(flightId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
