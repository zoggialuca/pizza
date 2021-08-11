package com.pizza.pizza.assembler;

import com.pizza.pizza.controller.PizzaIngredientController;
import com.pizza.pizza.dto.PizzaIngredientDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.stream.Collectors;

@Component
public class PizzaIngredientModelAssembler implements RepresentationModelAssembler<PizzaIngredientDTO, EntityModel<PizzaIngredientDTO>> {

    @Autowired PizzaModelAssembler pizzaModelAssembler;
    @Autowired IngredientModelAssembler ingredientModelAssembler;
    @Autowired UnitOfMeasureModelAssembler unitOfMeasureModelAssembler;

    @Override
    public EntityModel<PizzaIngredientDTO> toModel(PizzaIngredientDTO pizzaIngredientDTO){
        var pizzaDTO = pizzaIngredientDTO.getPizzaDTO();
        if (!pizzaDTO.hasLinks()) //prevents to repeat the links multiple times in case of multiple ingredients for the same pizza
        {
            pizzaDTO.add(pizzaModelAssembler.toModel(pizzaDTO).getLinks().stream().filter(link -> !link.getRel().value().equals("ingredients")).collect(Collectors.toList()));
        }

        var ingredientDTO = pizzaIngredientDTO.getIngredientDTO();
        if (!ingredientDTO.hasLinks()) //prevents to repeat the links multiple times in case of multiple ingredients for the same ingredient
        {
            ingredientDTO.add(ingredientModelAssembler.toModel(ingredientDTO).getLinks());
        }

        var unitOfMeasureDTO = pizzaIngredientDTO.getUnitOfMeasureDTO();
        if (!unitOfMeasureDTO.hasLinks()) //prevents to repeat the links multiple times in case of multiple ingredients for the same unit of measure
        {
            unitOfMeasureDTO.add(unitOfMeasureModelAssembler.toModel(unitOfMeasureDTO).getLinks());
        }

        return EntityModel.of(pizzaIngredientDTO
            , linkTo(methodOn(PizzaIngredientController.class).getPizzaIngredient(pizzaIngredientDTO.getId())).withSelfRel()
            );
    }

}
