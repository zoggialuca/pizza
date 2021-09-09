package com.pizza.pizza.dto;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
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
