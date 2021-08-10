package com.pizza.pizza.service;

import java.util.List;

import com.pizza.pizza.model.PizzaIngredient;
import com.pizza.pizza.repository.PizzaIngredientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PizzaIngredientRepositoryService extends RepositoryService<PizzaIngredient, Long, PizzaIngredientRepository>{

    @Autowired
    public PizzaIngredientRepositoryService(PizzaIngredientRepository pizzaIngredientRepository){
        super(pizzaIngredientRepository);
    }

    public List<PizzaIngredient> findByPizzaId(Long pizzaId){
        return jpaRepository.findByPizzaId(pizzaId);
    }
}
