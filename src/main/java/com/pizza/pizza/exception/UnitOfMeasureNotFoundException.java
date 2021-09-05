package com.pizza.pizza.exception;

public class UnitOfMeasureNotFoundException extends RuntimeException {

  public UnitOfMeasureNotFoundException(Long id) {
    super(String.format("Unit of measure %d not found", id));
  }

  public UnitOfMeasureNotFoundException(String name) {
    super(String.format("Unit of measure %s not found", name));
  }
}
