package com.pizza.pizza.assembler;

import com.pizza.pizza.controller.PizzaIngredientController;
import com.pizza.pizza.model.PizzaIngredient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.stream.Collectors;

@Component
public class PizzaIngredientModelAssembler implements RepresentationModelAssembler<PizzaIngredient, EntityModel<PizzaIngredient>> {

    @Autowired PizzaModelAssembler pizzaModelAssembler;
    @Autowired IngredientModelAssembler ingredientModelAssembler;
    @Autowired UnitOfMeasureModelAssembler unitOfMeasureModelAssembler;

    @Override
    public EntityModel<PizzaIngredient> toModel(PizzaIngredient pizzaIngredient){
        var pizza = pizzaIngredient.getPizza();
        if (!pizza.hasLinks()) //prevents to repeat the links multiple times in case of multiple ingredients for the same pizza
        {
            pizza.add(pizzaModelAssembler.toModel(pizza).getLinks().stream().filter(link -> link.getRel().value() != "ingredients").collect(Collectors.toList()));
        }

        var ingredient = pizzaIngredient.getIngredient();
        if (!ingredient.hasLinks()) //prevents to repeat the links multiple times in case of multiple ingredients for the same ingredient
        {
            ingredient.add(ingredientModelAssembler.toModel(ingredient).getLinks());
        }

        var unitOfMeasure = pizzaIngredient.getUnitOfMeasure();
        if (!unitOfMeasure.hasLinks()) //prevents to repeat the links multiple times in case of multiple ingredients for the same unit of measure
        {
            unitOfMeasure.add(unitOfMeasureModelAssembler.toModel(unitOfMeasure).getLinks());
        }

        return EntityModel.of(pizzaIngredient
            , linkTo(methodOn(PizzaIngredientController.class).getPizzaIngredient(pizzaIngredient.getId())).withSelfRel()
            );
    }

}
