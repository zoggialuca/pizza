package com.pizza.pizza.exception;

public class PizzaIngredientAlreadyExistsException extends RuntimeException{

    public PizzaIngredientAlreadyExistsException(Long pizzaId, Long ingredientId){
        super(String.format("Ingredient %d for pizza %d already exists", pizzaId, ingredientId));
    }
}
