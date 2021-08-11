package com.pizza.pizza.converter;

import com.pizza.pizza.dto.PizzaIngredientDTO;
import com.pizza.pizza.model.PizzaIngredient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PizzaIngredientConverter implements Converter<PizzaIngredient, PizzaIngredientDTO> {
    private final PizzaConverter pizzaConverter;
    private final IngredientConverter ingredientConverter;
    private final UnitOfMeasureConverter unitOfMeasureConverter;

    @Override
    public PizzaIngredientDTO toDTO(PizzaIngredient pizzaIngredient) {
        if (pizzaIngredient == null) {
            return null;
        }

        var pizzaIngredientDTO = new PizzaIngredientDTO();
        pizzaIngredientDTO.setId(pizzaIngredient.getId());
        pizzaIngredientDTO.setPizzaDTO(pizzaConverter.toDTO(pizzaIngredient.getPizza()));
        pizzaIngredientDTO.setIngredientDTO(ingredientConverter.toDTO(pizzaIngredient.getIngredient()));
        pizzaIngredientDTO.setQuantity(pizzaIngredient.getQuantity());
        pizzaIngredientDTO.setUnitOfMeasureDTO(unitOfMeasureConverter.toDTO(pizzaIngredient.getUnitOfMeasure()));
        return pizzaIngredientDTO;
    }

    @Override
    public Optional<PizzaIngredient> toOptionalEntity(PizzaIngredientDTO pizzaIngredientDTO) {
        if (pizzaIngredientDTO == null){
            return Optional.empty();
        }
        var pizzaIngredient = new PizzaIngredient(pizzaConverter.toOptionalEntity(pizzaIngredientDTO.getPizzaDTO()).get()
                , ingredientConverter.toOptionalEntity(pizzaIngredientDTO.getIngredientDTO()).get());
        pizzaIngredient.setId(pizzaIngredientDTO.getId());
        pizzaIngredient.setQuantity(pizzaIngredientDTO.getQuantity());
        pizzaIngredient.setUnitOfMeasure(unitOfMeasureConverter.toOptionalEntity(pizzaIngredientDTO.getUnitOfMeasureDTO()).get());
        return Optional.of(pizzaIngredient);
    }
}
