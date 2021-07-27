package com.pizza.pizza.service;

import java.util.Optional;

import com.pizza.pizza.model.Ingredient;
import com.pizza.pizza.repository.IngredientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientRepositoryService extends RepositoryService<Ingredient, Long, IngredientRepository>{
    
    @Autowired protected IngredientRepository ingredientRepository;
    
    public Optional<Ingredient> findByName(String name){
        return ingredientRepository.findByName(name);
    }
}
