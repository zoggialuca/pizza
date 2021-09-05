package com.pizza.pizza.exception;

public class IngredientAlreadyExistsException extends RuntimeException {

  public IngredientAlreadyExistsException(String name) {
    super(String.format("Ingredient %s already exists", name));
  }
}
