package com.pizza.pizza.dto;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@Relation(collectionRelation = "unitOfMeasureList", itemRelation = "unitOfMeasure")
public class UnitOfMeasureDTO extends RepresentationModel<UnitOfMeasureDTO> {
    private Long id;

    @NotBlank
    private String name;
}
