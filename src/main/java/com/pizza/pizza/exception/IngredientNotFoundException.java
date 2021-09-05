package com.pizza.pizza.exception;

public class IngredientNotFoundException extends RuntimeException {

  public IngredientNotFoundException(Long id) {
    super(String.format("Ingredient %d not found", id));
  }

  public IngredientNotFoundException(String name) {
    super(String.format("Ingredient %s not found", name));
  }

}
