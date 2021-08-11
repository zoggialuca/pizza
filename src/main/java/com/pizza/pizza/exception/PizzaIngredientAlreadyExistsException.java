package com.pizza.pizza.exception;

public class PizzaIngredientAlreadyExistsException extends RuntimeException{

    public PizzaIngredientAlreadyExistsException(String pizzaName, String ingredientName){
        super(String.format("Ingredient %s for pizza %s already exists", ingredientName, pizzaName));
    }
}
