package com.pizza.pizza.converter;

import com.pizza.pizza.dto.IngredientDTO;
import com.pizza.pizza.dto.PizzaDTO;
import com.pizza.pizza.dto.PizzaIngredientDTO;
import com.pizza.pizza.model.Pizza;
import com.pizza.pizza.model.PizzaIngredient;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PizzaConverter implements Converter<Pizza, PizzaDTO> {
    @Override
    public PizzaDTO toDTO(Pizza pizza) {
        if (pizza == null){
            return null;
        }

        var pizzaDTO = new PizzaDTO();
        pizzaDTO.setId(pizza.getId());
        pizzaDTO.setName(pizza.getName());
        pizzaDTO.setVegetarian(pizza.isVegetarian());
        return pizzaDTO;
    }

    @Override
    public Optional<Pizza> toOptionalEntity(PizzaDTO pizzaDTO) {
        if (pizzaDTO == null){
            return Optional.empty();
        }

        var pizza = new Pizza(pizzaDTO.getName());
        pizza.setVegetarian(pizzaDTO.isVegetarian());
        pizza.setId(pizzaDTO.getId());
        return Optional.of(pizza);
    }
}
