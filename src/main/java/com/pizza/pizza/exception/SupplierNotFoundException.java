package com.pizza.pizza.exception;

public class SupplierNotFoundException extends RuntimeException {
    public SupplierNotFoundException(Long id){
        super(String.format("Supplier %d not found", id));
    }

    public SupplierNotFoundException(String name){
        super(String.format("Supplier %s not found", name));
    }
}
