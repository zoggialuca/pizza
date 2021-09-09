package com.pizza.pizza.converter;

import com.pizza.pizza.dto.PizzaIngredientResponseDTO;
import com.pizza.pizza.model.PizzaIngredient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PizzaIngredientResponseEntityDTOBidirectionalConverter implements EntityToDTOConverter<PizzaIngredient, PizzaIngredientResponseDTO> {

  private final PizzaEntityDTOBidirectionalConverter pizzaConverter;
  private final IngredientEntityDTOBidirectionalConverter ingredientConverter;
  private final UnitOfMeasureEntityDTOBidirectionalConverter unitOfMeasureConverter;

  @Override
  public PizzaIngredientResponseDTO toDTO(PizzaIngredient pizzaIngredient) {
    if (pizzaIngredient == null) {
      return null;
    }

    return PizzaIngredientResponseDTO.builder()
                                     .id(pizzaIngredient.getId())
                                     .pizzaDTO(pizzaConverter.toDTO(pizzaIngredient.getPizza()))
                                     .ingredientDTO(ingredientConverter.toDTO(pizzaIngredient.getIngredient()))
                                     .quantity(pizzaIngredient.getQuantity())
                                     .unitOfMeasureDTO(unitOfMeasureConverter.toDTO(pizzaIngredient.getUnitOfMeasure()))
                                     .build();
  }
}
