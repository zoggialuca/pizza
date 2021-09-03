package com.pizza.pizza.exception;

import java.util.List;
import java.util.stream.Collectors;

public class IngredientAlreadySuppliedException extends RuntimeException{
  public IngredientAlreadySuppliedException(List<Long> ingredientIds) {
    super(String.format("Ingredients are already supplied by someone else: %s", ingredientIds.stream().map(String::valueOf).collect(Collectors.joining(","))));
  }

}
