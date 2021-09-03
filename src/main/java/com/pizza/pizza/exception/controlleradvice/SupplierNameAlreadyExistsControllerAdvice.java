package com.pizza.pizza.exception.controlleradvice;

import com.pizza.pizza.exception.SupplierNameAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class SupplierNameAlreadyExistsControllerAdvice {

  @ResponseBody
  @ExceptionHandler(SupplierNameAlreadyExistsException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  String handleSupplierNameAlreadyExistsException(SupplierNameAlreadyExistsException supplierNameAlreadyExistsException) {
    return supplierNameAlreadyExistsException.getMessage();
  }
}

