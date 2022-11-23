package ua.lviv.iot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.dto.assembler.AirportDtoAssembler;
import ua.lviv.iot.service.AirportProcedureService;

@RestController
@RequestMapping(value = "/api/airports")
public class AirportProcedureController {
    @Autowired
    private AirportProcedureService airportProcedureService;
    @Autowired
    private AirportDtoAssembler airportDtoAssembler;

    @PostMapping(value = "package")
    public ResponseEntity<?> addTenAirports(@RequestBody String airportName) {
        airportProcedureService.addTenAirports(airportName);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "add")
    public ResponseEntity<?> addAirport(@RequestBody String airportName) {
        airportProcedureService.addAirport(airportName);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "addBaseAirport/{airportId}/{airlineId}")
    public ResponseEntity<?> addBaseAirport(@PathVariable int airportId, @PathVariable int airlineId) {
        airportProcedureService.addBaseAirport(airportId, airlineId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
