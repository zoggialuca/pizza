package com.pizza.pizza.repository;

import com.pizza.pizza.model.IngredientSupplier;
import com.pizza.pizza.model.IngredientSupplierId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientSupplierRepository extends JpaRepository<IngredientSupplier, IngredientSupplierId> {
  List<IngredientSupplier> findAllByIngredientIdIn(List<Long> ingredientIds);
}
