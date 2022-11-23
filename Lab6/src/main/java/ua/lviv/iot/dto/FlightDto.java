package ua.lviv.iot.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;
import ua.lviv.iot.domain.Airport;
import ua.lviv.iot.domain.Plane;

import javax.persistence.OneToOne;

@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "flight", collectionRelation = "flights")
public class FlightDto extends RepresentationModel<FlightDto>{
    private Integer id;
}
