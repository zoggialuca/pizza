package com.pizza.pizza.exception.controlleradvice;

import com.pizza.pizza.exception.SupplierNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class SupplierNotFoundControllerAdvice {

  @ResponseBody
  @ExceptionHandler(SupplierNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String handleSupplierNotFoundException(SupplierNotFoundException supplierNotFoundException) {
    return supplierNotFoundException.getMessage();
  }
}