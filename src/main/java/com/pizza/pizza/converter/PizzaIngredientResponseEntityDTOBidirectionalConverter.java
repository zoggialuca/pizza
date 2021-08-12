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

        var pizzaIngredientResponseDTO = new PizzaIngredientResponseDTO();
        pizzaIngredientResponseDTO.setId(pizzaIngredient.getId());
        pizzaIngredientResponseDTO.setPizzaDTO(pizzaConverter.toDTO(pizzaIngredient.getPizza()));
        pizzaIngredientResponseDTO.setIngredientDTO(ingredientConverter.toDTO(pizzaIngredient.getIngredient()));
        pizzaIngredientResponseDTO.setQuantity(pizzaIngredient.getQuantity());
        pizzaIngredientResponseDTO.setUnitOfMeasureDTO(unitOfMeasureConverter.toDTO(pizzaIngredient.getUnitOfMeasure()));
        return pizzaIngredientResponseDTO;
    }
}
