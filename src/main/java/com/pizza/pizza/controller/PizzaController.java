package com.pizza.pizza.controller;

import java.util.Optional;
import java.util.stream.Collectors;

import com.pizza.pizza.assembler.PizzaModelAssembler;
import com.pizza.pizza.exception.PizzaNotFoundException;
import com.pizza.pizza.model.Pizza;
import com.pizza.pizza.service.PizzaRepositoryService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@CrossOrigin("*")
public class PizzaController {

	@Autowired private PizzaRepositoryService pizzaRepositoryService;
	@Autowired private PizzaModelAssembler pizzaModelAssembler;

	@GetMapping("/pizzas")
	public CollectionModel<EntityModel<Pizza>> getPizzas() {
		var pizzas = pizzaRepositoryService.findAll();
		return CollectionModel.of(pizzas.stream().map(pizzaModelAssembler::toModel).collect(Collectors.toList())
			, linkTo(methodOn(PizzaController.class).getPizzas()).withSelfRel()
			);
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
		return CollectionModel.of(pizzas.stream().map(pizzaModelAssembler::toModel).collect(Collectors.toList())
			, linkTo(methodOn(PizzaController.class).getPizzas(isVegetarian)).withSelfRel()
			);
	}
}
