package com.pizza.pizza.controller;

import java.util.Optional;
import java.util.stream.Collectors;

import com.pizza.pizza.assembler.PizzaIngredientModelAssembler;
import com.pizza.pizza.assembler.PizzaModelAssembler;
import com.pizza.pizza.exception.PizzaNotFoundException;
import com.pizza.pizza.model.Pizza;
import com.pizza.pizza.model.PizzaIngredient;
import com.pizza.pizza.service.PizzaIngredientRepositoryService;
import com.pizza.pizza.service.PizzaRepositoryService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

@RestController
@CrossOrigin("*")
public class PizzaController {

	@Autowired private PizzaRepositoryService pizzaRepositoryService;
	@Autowired private PizzaModelAssembler pizzaModelAssembler;
	@Autowired private PizzaIngredientRepositoryService pizzaIngredientRepositoryService;
	@Autowired private PizzaIngredientModelAssembler pizzaIngredientModelAssembler;

	@GetMapping("/pizzas")
	public CollectionModel<EntityModel<Pizza>> getPizzas() {
		var pizzas = pizzaRepositoryService.findAll();
		return CollectionModel.of(pizzas.stream().map(pizzaModelAssembler::toModel).collect(Collectors.toList()));
	}

	@GetMapping("/pizzas/{id}")
	public EntityModel<Pizza> getPizza(@PathVariable Long id) {
		var pizza = pizzaRepositoryService.findById(id);
		return pizzaModelAssembler.toModel(pizza.orElseThrow(() -> new PizzaNotFoundException(id)));
	}

	@GetMapping("/pizzas/name/{name}")
	public EntityModel<Pizza> getPizza(@PathVariable String name) {
		var pizza = pizzaRepositoryService.findByName(name);
		return pizzaModelAssembler.toModel(pizza.orElseThrow(() -> new PizzaNotFoundException(name)));
	}

	@GetMapping("/pizzas/isVegetarian/{isVegetarian}")
	public CollectionModel<EntityModel<Pizza>> getPizzas(@PathVariable Optional<Boolean> isVegetarian) {
		var pizzas = pizzaRepositoryService.findByIsVegetarian(isVegetarian);
		return CollectionModel.of(pizzas.stream().map(pizzaModelAssembler::toModel).collect(Collectors.toList()));
	}

	@GetMapping("/pizzas/{pizzaId}/ingredients")
	public CollectionModel<EntityModel<PizzaIngredient>> getPizzaIngredients(@PathVariable Long pizzaId) {
		var pizzaIngredients = pizzaIngredientRepositoryService.findByPizzaId(pizzaId);
		return CollectionModel.of(pizzaIngredients.stream().map(pizzaIngredientModelAssembler::toModel).collect(Collectors.toList()));
	}
}
