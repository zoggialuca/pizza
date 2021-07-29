package com.pizza.pizza.exception;

public class PizzaIngredientNotFoundException extends RuntimeException {

    public PizzaIngredientNotFoundException(Long id){
        super(String.format("Pizza ingredient %d not found", id));
    }
    
}
