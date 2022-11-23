
package ua.lviv.iot.dto.assembler;

import ua.lviv.iot.controller.PlaneController;
import ua.lviv.iot.domain.Plane;
import ua.lviv.iot.dto.PlaneDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PlaneDtoAssembler implements RepresentationModelAssembler<Plane, PlaneDto> {
    @Override
    public PlaneDto toModel(Plane entity) {
        PlaneDto planeDto = PlaneDto.builder()
                .id(entity.getId())
                .airline(entity.getAirline())
                .maxPassengers(entity.getMaxPassengers())
                .build();
        Link selfLink = linkTo(methodOn(PlaneController.class).getPlane(Math.toIntExact(planeDto.getId()))).withSelfRel();
        planeDto.add(selfLink);
        return planeDto;
    }

    @Override
    public CollectionModel<PlaneDto> toCollectionModel(Iterable<? extends Plane> entities) {
        CollectionModel<PlaneDto> planesDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(PlaneController.class).getAllPlanes()).withSelfRel();
        planesDtos.add(selfLink);
        return planesDtos;
    }

    public CollectionModel<PlaneDto> toCollectionModel(Iterable<? extends Plane> entities, Link link) {
        CollectionModel<PlaneDto> planesDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        planesDtos.add(link);
        return planesDtos;
    }
}
