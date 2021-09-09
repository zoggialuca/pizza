package com.pizza.pizza.converter;

import com.pizza.pizza.dto.PizzaDTO;
import com.pizza.pizza.model.Pizza;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class PizzaEntityDTOBidirectionalConverter implements EntityDTOBidirectionalConverter<Pizza, PizzaDTO> {

  @Override
  public PizzaDTO toDTO(Pizza pizza) {
    if (pizza == null) {
      return null;
    }
    return PizzaDTO.builder()
                   .id(pizza.getId())
                   .name(pizza.getName())
                   .isVegetarian(pizza.getIsVegetarian() != null && pizza.getIsVegetarian())
                   .build();
  }

  @Override
  public Optional<Pizza> toOptionalEntity(PizzaDTO pizzaDTO) {
    if (pizzaDTO == null) {
      return Optional.empty();
    }
    return Optional.of(Pizza.builder()
                            .name(pizzaDTO.getName())
                            .id(pizzaDTO.getId())
                            .isVegetarian(pizzaDTO.isVegetarian())
                            .build());

  }
}
