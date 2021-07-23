package com.pizza.pizza.controller;

import com.pizza.pizza.exception.PizzaNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PizzaNotFoundControllerAdvice {

    @ResponseBody
    @ExceptionHandler(PizzaNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String pizzaNotFoundExceptionHandler(PizzaNotFoundException pizzaNotFoundException){
        return pizzaNotFoundException.getMessage();
    }
}