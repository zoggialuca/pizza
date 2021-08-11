package com.pizza.pizza.repository;

import java.util.List;
import java.util.Optional;

import com.pizza.pizza.model.PizzaIngredient;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaIngredientRepository  extends JpaRepository<PizzaIngredient, Long>{
    List<PizzaIngredient> findByPizzaId(Long pizzaId);
    Optional<PizzaIngredient> findByPizzaIdAndIngredientId(Long pizzaId, Long ingredientId);
}
