package com.pizza.pizza.converter;

import com.pizza.pizza.dto.IngredientDTO;
import com.pizza.pizza.model.Ingredient;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class IngredientEntityDTOBidirectionalConverter implements EntityDTOBidirectionalConverter<Ingredient, IngredientDTO> {

  @Override
  public IngredientDTO toDTO(Ingredient ingredient) {
    if (ingredient == null) {
      return null;
    }

    var ingredientDTO = new IngredientDTO();
    ingredientDTO.setId(ingredient.getId());
    ingredientDTO.setName(ingredient.getName());
    ingredientDTO.setNotes(ingredient.getNotes());
    return ingredientDTO;
  }

  @Override
  public Optional<Ingredient> toOptionalEntity(IngredientDTO ingredientDTO) {
    if (ingredientDTO == null) {
      return Optional.empty();
    }

    var ingredient = new Ingredient(ingredientDTO.getName());
    ingredient.setId(ingredientDTO.getId());
    ingredient.setNotes(ingredientDTO.getNotes());
    return Optional.of(ingredient);
  }
}
