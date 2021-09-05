package com.pizza.pizza.repository;

import com.pizza.pizza.model.Ingredient;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

  Optional<Ingredient> findByName(String name);
}
