package com.pizza.pizza.exception.controlleradvice;

import com.pizza.pizza.exception.UnitOfMeasureNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UnitOfMeasureNotFoundControllerAdvice {
    
    @ResponseBody
    @ExceptionHandler(UnitOfMeasureNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String handleUnitOfMeasureNotFoundException(UnitOfMeasureNotFoundException pizzaNotFoundException){
        return pizzaNotFoundException.getMessage();
    }
}
