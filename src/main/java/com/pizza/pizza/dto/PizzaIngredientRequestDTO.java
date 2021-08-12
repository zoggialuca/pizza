package com.pizza.pizza.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class PizzaIngredientRequestDTO{
    private Long id;

    @NotNull
    private Long pizzaId;

    @NotNull
    private Long ingredientId;

    @DecimalMin(value = "0.0", inclusive = false)
    private Double quantity;

    @NotNull
    private Long unitOfMeasureId;
}
