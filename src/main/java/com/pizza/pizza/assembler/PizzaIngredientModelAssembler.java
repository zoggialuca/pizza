package com.pizza.pizza.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.pizza.pizza.controller.PizzaIngredientController;
import com.pizza.pizza.dto.PizzaIngredientResponseDTO;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class PizzaIngredientModelAssembler implements RepresentationModelAssembler<PizzaIngredientResponseDTO, EntityModel<PizzaIngredientResponseDTO>> {

  @Autowired
  PizzaModelAssembler pizzaModelAssembler;
  @Autowired
  IngredientModelAssembler ingredientModelAssembler;
  @Autowired
  UnitOfMeasureModelAssembler unitOfMeasureModelAssembler;

  @Override
  public EntityModel<PizzaIngredientResponseDTO> toModel(PizzaIngredientResponseDTO pizzaIngredientResponseDTO) {
    var pizzaDTO = pizzaIngredientResponseDTO.getPizzaDTO();
    if (!pizzaDTO.hasLinks()) { //prevents to repeat the links multiple times in case of multiple ingredient for the same pizza
      pizzaDTO.add(
          pizzaModelAssembler.toModel(pizzaDTO).getLinks().stream().filter(link -> !link.getRel().value().equals("ingredient")).collect(Collectors.toList()));
    }

    var ingredientDTO = pizzaIngredientResponseDTO.getIngredientDTO();
    if (!ingredientDTO.hasLinks()) { //prevents to repeat the links multiple times in case of multiple ingredient for the same ingredient
      ingredientDTO.add(ingredientModelAssembler.toModel(ingredientDTO).getLinks());
    }

    var unitOfMeasureDTO = pizzaIngredientResponseDTO.getUnitOfMeasureDTO();
    if (!unitOfMeasureDTO.hasLinks()) { //prevents to repeat the links multiple times in case of multiple ingredient for the same unit of measure
      unitOfMeasureDTO.add(unitOfMeasureModelAssembler.toModel(unitOfMeasureDTO).getLinks());
    }

    return EntityModel.of(pizzaIngredientResponseDTO,
        linkTo(methodOn(PizzaIngredientController.class).getPizzaIngredient(pizzaIngredientResponseDTO.getId())).withSelfRel()
    );
  }

}
