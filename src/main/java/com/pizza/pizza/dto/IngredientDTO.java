package com.pizza.pizza.dto;

import javax.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@Relation(collectionRelation = "ingredientList", itemRelation = "ingredient")
public class IngredientDTO extends RepresentationModel<IngredientDTO> {

  private Long id;

  @NotBlank
  private String name;

  private String notes;
}
