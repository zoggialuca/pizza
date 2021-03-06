package com.pizza.pizza.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import javax.validation.constraints.NotBlank;

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
