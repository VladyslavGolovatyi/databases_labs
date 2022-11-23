package ua.lviv.iot.dto.assembler;

import ua.lviv.iot.controller.AirlineController;
import ua.lviv.iot.domain.Airline;
import ua.lviv.iot.dto.AirlineDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AirlineDtoAssembler implements RepresentationModelAssembler<Airline, AirlineDto> {

    @Override
    public AirlineDto toModel(Airline entity) {
        AirlineDto airlineDto = AirlineDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .foundationYear(entity.getFoundationYear())
                .build();
        Link selfLink = linkTo(methodOn(AirlineController.class).getAirline(Math.toIntExact(airlineDto.getId()))).withSelfRel();
        airlineDto.add(selfLink);
        Link planesLink = linkTo(methodOn(AirlineController.class).getAllPlanesByAirlineId(Math.toIntExact(entity.getId()))).withRel("planes");
        airlineDto.add(planesLink);
        Link airportsLink = linkTo(methodOn(AirlineController.class).getAllAirportsByAirlineId(Math.toIntExact(entity.getId()))).withRel("airports");
        airlineDto.add(airportsLink);
        return airlineDto;
    }

    @Override
    public CollectionModel<AirlineDto> toCollectionModel(Iterable<? extends Airline> entities) {
        CollectionModel<AirlineDto> airlineDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(AirlineController.class).getAllAirlines()).withSelfRel();
        airlineDtos.add(selfLink);
        return airlineDtos;
    }

    public CollectionModel<AirlineDto> toCollectionModel(Iterable<? extends Airline> entities, Link link) {
        CollectionModel<AirlineDto> airlineDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        airlineDtos.add(link);
        return airlineDtos;
    }
}
