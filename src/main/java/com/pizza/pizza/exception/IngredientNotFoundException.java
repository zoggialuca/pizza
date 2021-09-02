package com.pizza.pizza.exception;

import java.util.List;
import java.util.stream.Collectors;

public class IngredientNotFoundException extends RuntimeException {
    public IngredientNotFoundException(Long id) {
        super(String.format("Ingredient %d not found", id));
    }

    public IngredientNotFoundException(String name) {
        super(String.format("Ingredient %s not found", name));
    }

    public IngredientNotFoundException(List<Long> ids) {
        super(String.format("Ingredients %s not found", ids.stream().map(String::valueOf).collect(Collectors.joining(","))));
    }
}
