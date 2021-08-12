package com.pizza.pizza.controller;

import com.pizza.pizza.assembler.PizzaIngredientModelAssembler;
import com.pizza.pizza.assembler.PizzaModelAssembler;
import com.pizza.pizza.dto.PizzaDTO;
import com.pizza.pizza.dto.PizzaIngredientResponseDTO;
import com.pizza.pizza.service.PizzaIngredientService;
import com.pizza.pizza.service.PizzaService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = {"${settings.cors_origin}"})
@RequiredArgsConstructor
public class PizzaController {

	private final PizzaService pizzaService;
	private final PizzaModelAssembler pizzaModelAssembler;
	private final PizzaIngredientService pizzaIngredientService;
	private final PizzaIngredientModelAssembler pizzaIngredientModelAssembler;

	@GetMapping("/pizzas")
	public ResponseEntity<CollectionModel<EntityModel<PizzaDTO>>> getPizzas() {
		var pizzas = pizzaService.findAll();
		return ResponseEntity.ok(CollectionModel.of(pizzas.stream().map(pizzaModelAssembler::toModel).collect(Collectors.toList())));
	}

	@GetMapping("/pizzas/{id}")
	public ResponseEntity<EntityModel<PizzaDTO>> getPizza(@PathVariable Long id) {
		return ResponseEntity.ok(pizzaModelAssembler.toModel(pizzaService.findById(id)));
	}

	@GetMapping(path = "/pizzas", params = {"name"})
	public ResponseEntity<EntityModel<PizzaDTO>> getPizza(@RequestParam String name) {
		return ResponseEntity.ok(pizzaModelAssembler.toModel(pizzaService.findByName(name)));
	}

	@GetMapping(path = "/pizzas", params = {"isVegetarian"})
	public ResponseEntity<CollectionModel<EntityModel<PizzaDTO>>> getPizzas(@RequestParam(required = false) boolean isVegetarian) {
		var pizzas = pizzaService.findByIsVegetarian(isVegetarian);
		return ResponseEntity.ok(CollectionModel.of(pizzas.stream().map(pizzaModelAssembler::toModel).collect(Collectors.toList())));
	}

	@GetMapping("/pizzas/{pizzaId}/ingredients")
	public CollectionModel<EntityModel<PizzaIngredientResponseDTO>> getPizzaIngredients(@PathVariable Long pizzaId) {
		var pizzaIngredients = pizzaIngredientService.findByPizzaId(pizzaId);
		return CollectionModel.of(pizzaIngredients.stream().map(pizzaIngredientModelAssembler::toModel).collect(Collectors.toList()));
	}

	@PostMapping("/pizzas")
	public ResponseEntity<EntityModel<PizzaDTO>> create(@RequestBody PizzaDTO pizzaDTO){
		var entityModel = pizzaModelAssembler.toModel(pizzaService.create(pizzaDTO));
		return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
				.body(entityModel);
	}

	@PutMapping("/pizzas/{id}")
	public ResponseEntity<EntityModel<PizzaDTO>> update(@RequestBody PizzaDTO pizzaDTO, @PathVariable Long id){
		var entityModel = pizzaModelAssembler.toModel(pizzaService.update(pizzaDTO, id));
		return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
				.body(entityModel);
	}

	@DeleteMapping("/pizzas/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		pizzaService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
