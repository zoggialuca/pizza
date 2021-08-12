package com.pizza.pizza.exception;

public class PizzaAlreadyExistsException extends RuntimeException{

    public PizzaAlreadyExistsException(String name){
        super(String.format("Pizza %s already exists", name));
    }
}
