package com.pizza.pizza.exception.controlleradvice;

import com.pizza.pizza.exception.IngredientAlreadySuppliedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class IngredientAlreadySuppliedExceptionControllerAdvice {

  @ResponseBody
  @ExceptionHandler(IngredientAlreadySuppliedException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  String handleIngredientAlreadySuppliedException(IngredientAlreadySuppliedException ingredientAlreadySuppliedException){
    return ingredientAlreadySuppliedException.getMessage();
  }
}
