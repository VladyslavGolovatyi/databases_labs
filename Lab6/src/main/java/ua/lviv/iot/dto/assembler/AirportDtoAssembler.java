
package ua.lviv.iot.dto.assembler;

import ua.lviv.iot.controller.AirportController;
import ua.lviv.iot.domain.Airport;
import ua.lviv.iot.dto.AirportDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AirportDtoAssembler implements RepresentationModelAssembler<Airport, AirportDto> {
    @Override
    public AirportDto toModel(Airport entity) {
        AirportDto airportDto = AirportDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
        Link selfLink = linkTo(methodOn(AirportController.class).getAirport(Math.toIntExact(airportDto.getId()))).withSelfRel();
        airportDto.add(selfLink);
        return airportDto;
    }

    @Override
    public CollectionModel<AirportDto> toCollectionModel(Iterable<? extends Airport> entities) {
        CollectionModel<AirportDto> airportDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(AirportController.class).getAllAirports()).withSelfRel();
        airportDtos.add(selfLink);
        return airportDtos;
    }

    public CollectionModel<AirportDto> toCollectionModel(Iterable<? extends Airport> entities, Link link) {
        CollectionModel<AirportDto> airportDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        airportDtos.add(link);
        return airportDtos;
    }
}
