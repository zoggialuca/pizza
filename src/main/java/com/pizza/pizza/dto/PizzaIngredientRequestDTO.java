package com.pizza.pizza.dto;

import javax.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class PizzaIngredientRequestDTO {

  private Long id;

  @NotNull
  private Long pizzaId;

  @NotNull
  private Long ingredientId;

  private Double quantity;

  @NotNull
  private Long unitOfMeasureId;
}
