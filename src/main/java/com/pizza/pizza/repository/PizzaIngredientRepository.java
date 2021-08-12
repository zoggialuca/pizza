package com.pizza.pizza.repository;

import java.util.List;
import java.util.Optional;

import com.pizza.pizza.model.PizzaIngredient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PizzaIngredientRepository  extends JpaRepository<PizzaIngredient, Long>{
    List<PizzaIngredient> findByPizzaId(Long pizzaId);
    Optional<PizzaIngredient> findByPizzaIdAndIngredientId(Long pizzaId, Long ingredientId);

    /*
    @Query(value = "insert into PizzaIngredient(pizza.id, ingredient.id, quantity, unitOfMeasure.id)" +
            "values(:pizzaId, :ingredientId, :quantity, :unitOfMeasureId)"
    )
    @Modifying
    PizzaIngredient insertJPQL(Long pizzaId, Long ingredientId, double quantity, Long unitOfMeasureId);
    */

    @Query(value = "insert into pizza_ingredient(pizza_id, ingredient_id, quantity, unit_of_measure_id)" +
            "values(:pizzaId, :ingredientId, :quantity, :unitOfMeasureId)"
            , nativeQuery = true
    )
    @Modifying
    PizzaIngredient insertNative(Long pizzaId, Long ingredientId, double quantity, Long unitOfMeasureId);
}
