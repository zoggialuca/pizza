package com.pizza.pizza.exception.controlleradvice;

import com.pizza.pizza.exception.PizzaIngredientAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PizzaIngredientAlreadyExistsControllerAdvice {

  @ResponseBody
  @ExceptionHandler(PizzaIngredientAlreadyExistsException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  String handlePizzaIngredientAlreadyExistsException(PizzaIngredientAlreadyExistsException pizzaIngredientAlreadyExistsException) {
    return pizzaIngredientAlreadyExistsException.getMessage();
  }
}
