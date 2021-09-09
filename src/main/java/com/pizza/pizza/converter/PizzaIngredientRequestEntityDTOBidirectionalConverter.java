package com.pizza.pizza.converter;

import com.pizza.pizza.dto.PizzaIngredientRequestDTO;
import com.pizza.pizza.exception.IngredientNotFoundException;
import com.pizza.pizza.exception.PizzaNotFoundException;
import com.pizza.pizza.exception.UnitOfMeasureNotFoundException;
import com.pizza.pizza.model.PizzaIngredient;
import com.pizza.pizza.repository.IngredientRepository;
import com.pizza.pizza.repository.PizzaRepository;
import com.pizza.pizza.repository.UnitOfMeasureRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PizzaIngredientRequestEntityDTOBidirectionalConverter implements DTOToEntityConverter<PizzaIngredientRequestDTO, PizzaIngredient> {

  private final PizzaRepository pizzaRepository;
  private final IngredientRepository ingredientRepository;
  private final UnitOfMeasureRepository unitOfMeasureRepository;

  @Override
  public Optional<PizzaIngredient> toOptionalEntity(PizzaIngredientRequestDTO pizzaIngredientRequestDTO) {
    if (pizzaIngredientRequestDTO == null) {
      return Optional.empty();
    }

    var pizzaId = pizzaIngredientRequestDTO.getPizzaId();
    var pizza = pizzaRepository.findById(pizzaId)
                               .orElseThrow(() -> new PizzaNotFoundException(pizzaId));

    var ingredientId = pizzaIngredientRequestDTO.getIngredientId();
    var ingredient = ingredientRepository.findById(ingredientId)
                                         .orElseThrow(() -> new IngredientNotFoundException(ingredientId));

    var unitOfMeasureId = pizzaIngredientRequestDTO.getUnitOfMeasureId();
    var unitOfMeasure = unitOfMeasureRepository.findById(unitOfMeasureId)
                                               .orElseThrow(() -> new UnitOfMeasureNotFoundException(unitOfMeasureId));

    var pizzaIngredient = PizzaIngredient.builder()
                                         .pizza(pizza)
                                         .ingredient(ingredient)
                                         .id(pizzaIngredientRequestDTO.getId())
                                         .quantity(pizzaIngredientRequestDTO.getQuantity())
                                         .unitOfMeasure(unitOfMeasure)
                                         .build();
    return Optional.of(pizzaIngredient);
  }
}
