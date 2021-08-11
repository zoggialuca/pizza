package com.pizza.pizza.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class PizzaDTO extends RepresentationModel<PizzaDTO> {
    private Long id;

    @NotBlank
    private String name;

    private boolean isVegetarian;
}
