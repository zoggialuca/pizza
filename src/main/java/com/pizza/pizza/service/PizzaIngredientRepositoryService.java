package com.pizza.pizza.service;

import java.util.List;

import com.pizza.pizza.model.PizzaIngredient;
import com.pizza.pizza.repository.PizzaIngredientRepository;

import org.springframework.stereotype.Service;

@Service
public class PizzaIngredientRepositoryService extends RepositoryService<PizzaIngredient, Long>{

    protected PizzaIngredientRepository pizzaIngredientRepository;
    
    public PizzaIngredientRepositoryService(PizzaIngredientRepository pizzaIngredientRepository) {
        super(pizzaIngredientRepository);
        this.pizzaIngredientRepository = pizzaIngredientRepository;
    }

    public List<PizzaIngredient> findByPizzaId(Long pizzaId){
        return pizzaIngredientRepository.findByPizzaId(pizzaId);
    }
}
