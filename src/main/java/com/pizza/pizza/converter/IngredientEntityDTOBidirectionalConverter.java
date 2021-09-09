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

    return IngredientDTO.builder()
                        .id(ingredient.getId())
                        .name(ingredient.getName())
                        .notes(ingredient.getNotes())
                        .build();
  }

  @Override
  public Optional<Ingredient> toOptionalEntity(IngredientDTO ingredientDTO) {
    if (ingredientDTO == null) {
      return Optional.empty();
    }

    var ingredient = Ingredient.builder()
                               .name(ingredientDTO.getName())
                               .id(ingredientDTO.getId())
                               .notes(ingredientDTO.getNotes())
                               .build();
    return Optional.of(ingredient);
  }
}
