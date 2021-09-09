package com.pizza.pizza.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.pizza.pizza.controller.IngredientController;
import com.pizza.pizza.dto.IngredientDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class IngredientModelAssembler implements RepresentationModelAssembler<IngredientDTO, EntityModel<IngredientDTO>> {

  @Override
  public EntityModel<IngredientDTO> toModel(IngredientDTO ingredientDTO) {
    return EntityModel.of(ingredientDTO,
        linkTo(methodOn(IngredientController.class).getIngredient(ingredientDTO.getId())).withSelfRel(),
        linkTo(methodOn(IngredientController.class).getIngredients()).withRel("ingredient")
    );
  }
}
