package com.pizza.pizza.assembler;

import com.pizza.pizza.controller.IngredientController;
import com.pizza.pizza.dto.IngredientDTO;
import com.pizza.pizza.model.Ingredient;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class IngredientModelAssembler implements RepresentationModelAssembler<IngredientDTO, EntityModel<IngredientDTO>> {
    
    @Override
    public EntityModel<IngredientDTO> toModel(IngredientDTO ingredientDTO){
        return EntityModel.of(ingredientDTO
            , linkTo(methodOn(IngredientController.class).getIngredient(ingredientDTO.getId())).withSelfRel()
            , linkTo(methodOn(IngredientController.class).getIngredients()).withRel("ingredients")
            );
    }
}
