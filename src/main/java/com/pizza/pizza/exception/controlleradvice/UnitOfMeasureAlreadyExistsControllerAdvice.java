package com.pizza.pizza.exception.controlleradvice;

import com.pizza.pizza.exception.UnitOfMeasureAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UnitOfMeasureAlreadyExistsControllerAdvice {

  @ResponseBody
  @ExceptionHandler(UnitOfMeasureAlreadyExistsException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  String handleUnitOfMeasureNotFoundException(UnitOfMeasureAlreadyExistsException unitOfMeasureAlreadyExistsException) {
    return unitOfMeasureAlreadyExistsException.getMessage();
  }
}
