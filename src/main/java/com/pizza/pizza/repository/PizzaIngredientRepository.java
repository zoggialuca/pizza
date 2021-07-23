package com.pizza.pizza.repository;

import com.pizza.pizza.model.PizzaIngredient;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaIngredientRepository  extends JpaRepository<PizzaIngredient, Long>{
    
}
