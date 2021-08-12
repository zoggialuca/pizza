package com.pizza.pizza.repository;

import com.pizza.pizza.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IngredientRepository extends JpaRepository<Ingredient, Long>{
    Optional<Ingredient> findByName(String name);
}
