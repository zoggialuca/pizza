package com.pizza.pizza.converter;

import com.pizza.pizza.dto.PizzaIngredientRequestDTO;
import com.pizza.pizza.dto.PizzaIngredientResponseDTO;
import com.pizza.pizza.exception.IngredientNotFoundException;
import com.pizza.pizza.exception.PizzaNotFoundException;
import com.pizza.pizza.exception.UnitOfMeasureNotFoundException;
import com.pizza.pizza.model.PizzaIngredient;
import com.pizza.pizza.repository.IngredientRepository;
import com.pizza.pizza.repository.PizzaRepository;
import com.pizza.pizza.repository.UnitOfMeasureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PizzaIngredientRequestConverter implements Converter<PizzaIngredient, PizzaIngredientRequestDTO>{

    private final PizzaRepository pizzaRepository;
    private final IngredientRepository ingredientRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    @Override
    public PizzaIngredientRequestDTO toDTO(PizzaIngredient pizzaIngredient) {
        if (pizzaIngredient == null) {
            return null;
        }

        var pizzaIngredientRequestDTO = new PizzaIngredientRequestDTO();
        pizzaIngredientRequestDTO.setId(pizzaIngredient.getId());
        pizzaIngredientRequestDTO.setPizzaId(pizzaIngredient.getPizza().getId());
        pizzaIngredientRequestDTO.setIngredientId(pizzaIngredient.getIngredient().getId());
        pizzaIngredientRequestDTO.setQuantity(pizzaIngredient.getQuantity());
        pizzaIngredientRequestDTO.setUnitOfMeasureId(pizzaIngredient.getUnitOfMeasure().getId());
        return pizzaIngredientRequestDTO;
    }

    @Override
    public Optional<PizzaIngredient> toOptionalEntity(PizzaIngredientRequestDTO pizzaIngredientRequestDTO) {
        if (pizzaIngredientRequestDTO == null){
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

        var pizzaIngredient = new PizzaIngredient(pizza, ingredient);
        pizzaIngredient.setId(pizzaIngredientRequestDTO.getId());
        pizzaIngredient.setQuantity(pizzaIngredientRequestDTO.getQuantity());
        pizzaIngredient.setUnitOfMeasure(unitOfMeasure);
        return Optional.of(pizzaIngredient);
    }
}
