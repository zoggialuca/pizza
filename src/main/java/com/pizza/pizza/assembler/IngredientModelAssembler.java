package com.pizza.pizza.assembler;

import com.pizza.pizza.controller.IngredientController;
import com.pizza.pizza.model.Ingredient;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class IngredientModelAssembler implements RepresentationModelAssembler<Ingredient, EntityModel<Ingredient>> {
    
    @Override
    public EntityModel<Ingredient> toModel(Ingredient ingredient){
        return EntityModel.of(ingredient
            , linkTo(methodOn(IngredientController.class).getIngredient(ingredient.getId())).withSelfRel()
            , linkTo(methodOn(IngredientController.class).getIngredients()).withRel("ingredients")
            );
    }
}
