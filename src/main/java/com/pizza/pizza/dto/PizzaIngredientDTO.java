package com.pizza.pizza.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class PizzaIngredientDTO extends RepresentationModel<PizzaIngredientDTO> {
    private Long id;

    @NotNull
    private PizzaDTO pizzaDTO;

    @NotNull
    private IngredientDTO ingredientDTO;

    @NotBlank
    private Double quantity;

    @NotNull
    private UnitOfMeasureDTO unitOfMeasureDTO;
}
