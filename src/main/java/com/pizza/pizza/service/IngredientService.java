package com.pizza.pizza.service;

import com.pizza.pizza.converter.EntityDTOBidirectionalConverter;
import com.pizza.pizza.dto.IngredientDTO;
import com.pizza.pizza.exception.IngredientAlreadyExistsException;
import com.pizza.pizza.exception.IngredientNotFoundException;
import com.pizza.pizza.model.Ingredient;
import com.pizza.pizza.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IngredientService {
    private final EntityDTOBidirectionalConverter<Ingredient, IngredientDTO> converter;
    private final IngredientRepository ingredientRepository;

    public List<IngredientDTO> findAll() {
        return ingredientRepository.findAll().stream().map(converter::toDTO)
                .collect(Collectors.toList());
    }

    public List<IngredientDTO> findBySupplierId(Long supplierId){
        return ingredientRepository.findBySupplierId(supplierId).stream().map(converter::toDTO).collect(Collectors.toList());
    }

    public IngredientDTO findById(Long id) {
        return ingredientRepository.findById(id).map(converter::toDTO)
                .orElseThrow(() -> new IngredientNotFoundException(id));
    }

    public IngredientDTO findByName(String name) {
        return ingredientRepository.findByName(name).map(converter::toDTO)
                .orElseThrow(() -> new IngredientNotFoundException(name));
    }

    public IngredientDTO create(IngredientDTO ingredientDTO) {
        var ingredient = converter.toOptionalEntity(ingredientDTO);
        return ingredient.map(ingredientRepository::save).map(converter::toDTO).orElse(null);
    }

    public IngredientDTO update(IngredientDTO ingredientDTO, Long id) {
        if (ingredientRepository.findByName(ingredientDTO.getName()).isPresent()) {
            throw new IngredientAlreadyExistsException(ingredientDTO.getName());
        }
        return ingredientRepository.findById(id)
                .map(ingredient -> {
                    ingredient.setNotes(ingredientDTO.getNotes());
                    return ingredientRepository.save(ingredient);
                })
                .map(converter::toDTO)
                .orElseThrow(() -> new IngredientNotFoundException(id));
    }

    public void delete(Long id) {
        ingredientRepository.deleteById(id);
    }
}
