package com.pizza.pizza.exception.controlleradvice;

import com.pizza.pizza.exception.PizzaAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PizzaAlreadyExistsControllerAdvice {

  @ResponseBody
  @ExceptionHandler(PizzaAlreadyExistsException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  String handlePizzaAlreadyExistsException(PizzaAlreadyExistsException pizzaAlreadyExistsException) {
    return pizzaAlreadyExistsException.getMessage();
  }
}
