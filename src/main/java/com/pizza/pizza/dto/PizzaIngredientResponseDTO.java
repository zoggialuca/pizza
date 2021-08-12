package com.pizza.pizza.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class PizzaIngredientResponseDTO extends RepresentationModel<PizzaIngredientResponseDTO> {
    private Long id;

    @NotNull
    private PizzaDTO pizzaDTO;

    @NotNull
    private IngredientDTO ingredientDTO;

    @DecimalMin(value = "0.0", inclusive = false)
    private Double quantity;

    @NotNull
    private UnitOfMeasureDTO unitOfMeasureDTO;
}
