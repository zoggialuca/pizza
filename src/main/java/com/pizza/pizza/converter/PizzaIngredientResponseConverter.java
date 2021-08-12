package com.pizza.pizza.converter;

import com.pizza.pizza.dto.PizzaIngredientResponseDTO;
import com.pizza.pizza.model.PizzaIngredient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PizzaIngredientResponseConverter implements Converter<PizzaIngredient, PizzaIngredientResponseDTO> {
    private final PizzaConverter pizzaConverter;
    private final IngredientConverter ingredientConverter;
    private final UnitOfMeasureConverter unitOfMeasureConverter;

    @Override
    public PizzaIngredientResponseDTO toDTO(PizzaIngredient pizzaIngredient) {
        if (pizzaIngredient == null) {
            return null;
        }

        var pizzaIngredientResponseDTO = new PizzaIngredientResponseDTO();
        pizzaIngredientResponseDTO.setId(pizzaIngredient.getId());
        pizzaIngredientResponseDTO.setPizzaDTO(pizzaConverter.toDTO(pizzaIngredient.getPizza()));
        pizzaIngredientResponseDTO.setIngredientDTO(ingredientConverter.toDTO(pizzaIngredient.getIngredient()));
        pizzaIngredientResponseDTO.setQuantity(pizzaIngredient.getQuantity());
        pizzaIngredientResponseDTO.setUnitOfMeasureDTO(unitOfMeasureConverter.toDTO(pizzaIngredient.getUnitOfMeasure()));
        return pizzaIngredientResponseDTO;
    }

    @Override
    public Optional<PizzaIngredient> toOptionalEntity(PizzaIngredientResponseDTO pizzaIngredientResponseDTO) {
        if (pizzaIngredientResponseDTO == null){
            return Optional.empty();
        }
        var pizzaIngredient = new PizzaIngredient(pizzaConverter.toOptionalEntity(pizzaIngredientResponseDTO.getPizzaDTO()).get()
                , ingredientConverter.toOptionalEntity(pizzaIngredientResponseDTO.getIngredientDTO()).get());
        pizzaIngredient.setId(pizzaIngredientResponseDTO.getId());
        pizzaIngredient.setQuantity(pizzaIngredientResponseDTO.getQuantity());
        pizzaIngredient.setUnitOfMeasure(unitOfMeasureConverter.toOptionalEntity(pizzaIngredientResponseDTO.getUnitOfMeasureDTO()).get());
        return Optional.of(pizzaIngredient);
    }
}
