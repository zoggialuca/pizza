package com.pizza.pizza.repository;

import java.util.Optional;

import com.pizza.pizza.model.Ingredient;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long>{
    Optional<Ingredient> findByName(String name);
}
