package com.pizza.pizza.exception;

public class UnitOfMeasureAlreadyExistsException extends RuntimeException{

    public UnitOfMeasureAlreadyExistsException(String name){
        super(String.format("Unit of measure %s already exists", name));
    }
}
