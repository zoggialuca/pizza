package com.pizza.pizza.repository;

import java.util.List;

import com.pizza.pizza.model.PizzaIngredient;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaIngredientRepository  extends JpaRepository<PizzaIngredient, Long>{
    List<PizzaIngredient> findByPizzaId(Long pizzaId);
}
