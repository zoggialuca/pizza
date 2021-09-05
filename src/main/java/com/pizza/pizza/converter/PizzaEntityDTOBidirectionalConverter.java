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
    var pizzaDTO = new PizzaDTO();
    pizzaDTO.setId(pizza.getId());
    pizzaDTO.setName(pizza.getName());
    pizzaDTO.setVegetarian(pizza.getIsVegetarian() != null && pizza.getIsVegetarian());
    return pizzaDTO;
  }

  @Override
  public Optional<Pizza> toOptionalEntity(PizzaDTO pizzaDTO) {
    if (pizzaDTO == null) {
      return Optional.empty();
    }
    var pizza = new Pizza(pizzaDTO.getName());
    pizza.setIsVegetarian(pizzaDTO.isVegetarian());
    pizza.setId(pizzaDTO.getId());
    return Optional.of(pizza);
  }
}
