package com.pizza.pizza.exception.controlleradvice;

import com.pizza.pizza.exception.PizzaIngredientNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PizzaIngredientNotFoundControllerAdvice {
    
    @ResponseBody
    @ExceptionHandler(PizzaIngredientNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String pizzaIngredientNotFoundExceptionHandler(PizzaIngredientNotFoundException pizzaIngredientNotFoundException){
        return pizzaIngredientNotFoundException.getMessage();
    }
}
