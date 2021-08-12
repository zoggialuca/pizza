package com.pizza.pizza.exception.controlleradvice;

import com.pizza.pizza.exception.IngredientNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class IngredientNotFoundControllerAdvice {

    @ResponseBody
    @ExceptionHandler(IngredientNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String handleIngredientNotFoundException(IngredientNotFoundException ingredientNotFoundException){
        return ingredientNotFoundException.getMessage();
    }
}