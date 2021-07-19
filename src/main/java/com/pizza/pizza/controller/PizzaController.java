package com.pizza.pizza.controller;

import java.util.stream.Collectors;

import com.pizza.pizza.assembler.PizzaModelAssembler;
import com.pizza.pizza.exception.PizzaNotFoundException;
import com.pizza.pizza.model.Pizza;
import com.pizza.pizza.repository.PizzaRepository;

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

	@Autowired private PizzaRepository pizzaRepository;
	@Autowired private PizzaModelAssembler pizzaModelAssembler;

	@GetMapping("/pizzas")
	public CollectionModel<EntityModel<Pizza>> getPizzas() {
		var pizzas = pizzaRepository.findAll().stream().map(pizzaModelAssembler::toModel).collect(Collectors.toList());
		return CollectionModel.of(pizzas, linkTo(methodOn(PizzaController.class).getPizzas()).withSelfRel());
	}

	@GetMapping("/pizzas/{id}")
	public EntityModel<Pizza> getPizza(@PathVariable Long id) {
		var pizza = pizzaRepository.findById(id).orElseThrow(() -> new PizzaNotFoundException(id));
		return pizzaModelAssembler.toModel(pizza);
	}
}
