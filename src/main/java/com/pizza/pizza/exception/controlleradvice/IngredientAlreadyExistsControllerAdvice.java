package com.pizza.pizza.exception.controlleradvice;

import com.pizza.pizza.exception.IngredientAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class IngredientAlreadyExistsControllerAdvice {

    @ResponseBody
    @ExceptionHandler(IngredientAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String handleIngredientAlreadyExistsException(IngredientAlreadyExistsException ingredientAlreadyExistsException){
        return ingredientAlreadyExistsException.getMessage();
    }
}
