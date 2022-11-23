package ua.lviv.iot.dto.assembler;

import ua.lviv.iot.controller.FlightController;
import ua.lviv.iot.domain.Flight;
import ua.lviv.iot.dto.FlightDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class FlightDtoAssembler implements RepresentationModelAssembler<Flight, FlightDto> {
    @Override
    public FlightDto toModel(Flight entity) {
        FlightDto flightDto = FlightDto.builder()
                .id(entity.getId())
                .build();
        Link selfLink = linkTo(methodOn(FlightController.class).getFlight(Math.toIntExact(flightDto.getId()))).withSelfRel();
        flightDto.add(selfLink);
        return flightDto;
    }

    @Override
    public CollectionModel<FlightDto> toCollectionModel(Iterable<? extends Flight> entities) {
        CollectionModel<FlightDto> flightDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(FlightController.class).getAllFlights()).withSelfRel();
        flightDtos.add(selfLink);
        return flightDtos;
    }

    public CollectionModel<FlightDto> toCollectionModel(Iterable<? extends Flight> entities, Link link) {
        CollectionModel<FlightDto> flightDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        flightDtos.add(link);
        return flightDtos;
    }
}
