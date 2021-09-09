package com.pizza.pizza.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Getter
@Builder
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@Relation(collectionRelation = "pizzaIngredientList", itemRelation = "pizzaIngredient")
public class PizzaIngredientResponseDTO extends RepresentationModel<PizzaIngredientResponseDTO> {

  private Long id;

  @NotNull
  @JsonProperty("pizza")
  private PizzaDTO pizzaDTO;

  @NotNull
  @JsonProperty("ingredient")
  private IngredientDTO ingredientDTO;

  private Double quantity;

  @NotNull
  @JsonProperty("unitOfMeasure")
  private UnitOfMeasureDTO unitOfMeasureDTO;
}
