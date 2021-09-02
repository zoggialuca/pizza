package com.pizza.pizza.service;

import com.pizza.pizza.converter.EntityDTOBidirectionalConverter;
import com.pizza.pizza.dto.IngredientDTO;
import com.pizza.pizza.model.Ingredient;
import com.pizza.pizza.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IngredientsFinder {

    private final EntityDTOBidirectionalConverter<Ingredient, IngredientDTO> converter;
    private final IngredientRepository ingredientRepository;

    public List<IngredientDTO> findBySupplierId(Long supplierId){
        return ingredientRepository.findBySupplierId(supplierId).stream().map(converter::toDTO).collect(Collectors.toList());
    }

    public List<Ingredient> findByIdIn(List<Long> ingredientIds){
        return ingredientRepository.findByIdIn(ingredientIds);
    }

}
