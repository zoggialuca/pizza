package com.pizza.pizza.service;

import com.pizza.pizza.converter.Converter;
import com.pizza.pizza.dto.PizzaIngredientDTO;
import com.pizza.pizza.exception.*;
import com.pizza.pizza.model.PizzaIngredient;
import com.pizza.pizza.repository.IngredientRepository;
import com.pizza.pizza.repository.PizzaIngredientRepository;
import com.pizza.pizza.repository.PizzaRepository;
import com.pizza.pizza.repository.UnitOfMeasureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PizzaIngredientService {
    private final Converter<PizzaIngredient, PizzaIngredientDTO> converter;
    private final PizzaIngredientRepository pizzaIngredientRepository;
    private final PizzaRepository pizzaRepository;
    private final IngredientRepository ingredientRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public PizzaIngredientDTO findById(Long id) {
        return pizzaIngredientRepository.findById(id)
                .map(converter::toDTO)
                .orElseThrow(() -> new IngredientNotFoundException(id));
    }

    public PizzaIngredientDTO create(PizzaIngredientDTO pizzaIngredientDTO) {
        var ingredient = converter.toOptionalEntity(pizzaIngredientDTO);
        return ingredient.map(pizzaIngredientRepository::save)
                .map(converter::toDTO)
                .orElse(null);
    }

    @Transactional
    public PizzaIngredientDTO update(PizzaIngredientDTO pizzaIngredientDTO, Long id) {
        var pizzaName = pizzaIngredientDTO.getPizzaDTO().getName();
        var pizza = pizzaRepository.findByName(pizzaName)
                .orElseThrow(() -> new PizzaNotFoundException(pizzaName));

        var ingredientName = pizzaIngredientDTO.getIngredientDTO().getName();
        var ingredient = ingredientRepository.findByName(ingredientName)
                .orElseThrow(() -> new IngredientNotFoundException(ingredientName));

        var pizzaIngredient = pizzaIngredientRepository.findByPizzaIdAndIngredientId(pizza.getId(), ingredient.getId())
                .orElseGet(() -> pizzaIngredientRepository.findById(pizzaIngredientDTO.getId())
                        .orElseThrow(() -> new PizzaIngredientNotFoundException(id))
                );
        if (!pizzaIngredient.getId().equals(id)) {
            //we have an error if the couple already exists and is linked to another record
            throw new PizzaIngredientAlreadyExistsException(pizzaName, ingredientName);
        }

        if (!(pizzaIngredient.getPizza().getId().equals(pizza.getId())
                && pizzaIngredient.getIngredient().getId().equals(ingredient.getId())
        )) {
            //at least, one key field has been changed. thus, we delete the existing record and we create a new one
            pizzaIngredientRepository.delete(pizzaIngredient);
            pizzaIngredient = new PizzaIngredient(pizza, ingredient);
        }

        var unitOfMeasureName = pizzaIngredientDTO.getUnitOfMeasureDTO().getName();
        var unitOfMeasure = unitOfMeasureRepository.findByName(unitOfMeasureName)
                .orElseThrow(() -> new UnitOfMeasureNotFoundException(unitOfMeasureName));

        pizzaIngredient.setQuantity(pizzaIngredientDTO.getQuantity());
        pizzaIngredient.setUnitOfMeasure(unitOfMeasure);
        return converter.toDTO(pizzaIngredientRepository.save(pizzaIngredient));
    }

    public void delete(Long id) {
        pizzaIngredientRepository.deleteById(id);
    }

    public List<PizzaIngredientDTO> findByPizzaId(Long pizzaId) {
        return pizzaIngredientRepository.findByPizzaId(pizzaId).stream()
                .map(converter::toDTO)
                .collect(Collectors.toList());
    }
}
