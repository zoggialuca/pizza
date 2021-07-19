package com.pizza.pizza.exception;

public class PizzaNotFoundException extends RuntimeException {
    public PizzaNotFoundException(Long id){
        super(String.format("Pizza %d not found", id));
    }
    
    public PizzaNotFoundException(String name){
        super(String.format("Pizza %s not found", name));
    }
}
