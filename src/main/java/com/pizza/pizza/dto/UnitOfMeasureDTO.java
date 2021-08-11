package com.pizza.pizza.dto;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class UnitOfMeasureDTO extends RepresentationModel<UnitOfMeasureDTO> {
    private Long id;

    @NotBlank
    private String name;
}
