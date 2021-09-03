package com.pizza.pizza.exception;

public class SupplierNameAlreadyExistsException extends RuntimeException{


  public SupplierNameAlreadyExistsException(String name) {
    super(String.format("Supplier with given name already exists: %s", name));
  }

}
