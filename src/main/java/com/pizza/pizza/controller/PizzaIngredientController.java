package com.pizza.pizza.controller;

import com.pizza.pizza.assembler.PizzaIngredientModelAssembler;
import com.pizza.pizza.exception.PizzaIngredientNotFoundException;
import com.pizza.pizza.model.PizzaIngredient;
import com.pizza.pizza.service.PizzaIngredientRepositoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class PizzaIngredientController {
    
	@Autowired private PizzaIngredientRepositoryService pizzaIngredientRepositoryService;
	@Autowired private PizzaIngredientModelAssembler pizzaIngredientModelAssembler;

	@GetMapping("/pizza_ingredient/{id}")
	public EntityModel<PizzaIngredient> getPizzaIngredient(@PathVariable Long id) {
		var pizzaIngredient = pizzaIngredientRepositoryService.findById(id);
		return pizzaIngredientModelAssembler.toModel(pizzaIngredient.orElseThrow(() -> new PizzaIngredientNotFoundException(id)));
	}
}
