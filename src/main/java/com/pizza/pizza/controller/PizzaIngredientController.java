package com.pizza.pizza.controller;

import java.util.Optional;

import com.pizza.pizza.assembler.PizzaIngredientModelAssembler;
import com.pizza.pizza.exception.PizzaIngredientNotFoundException;
import com.pizza.pizza.model.PizzaIngredient;
import com.pizza.pizza.service.PizzaIngredientRepositoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin("*")
public class PizzaIngredientController {
    
	@Autowired private PizzaIngredientRepositoryService pizzaIngredientRepositoryService;
	@Autowired private PizzaIngredientModelAssembler pizzaIngredientModelAssembler;

	@GetMapping("/pizza_ingredient/{id}")
	public ResponseEntity<EntityModel<PizzaIngredient>> getPizzaIngredient(@PathVariable Long id) {
		var pizzaIngredient = pizzaIngredientRepositoryService.findById(id);
		return ResponseEntity.ok(pizzaIngredientModelAssembler.toModel(pizzaIngredient.orElseThrow(() -> new PizzaIngredientNotFoundException(id))));
	}

	@PostMapping("/pizza_ingredient")
	public ResponseEntity<?> create(@RequestBody PizzaIngredient pizzaIngredient){
		try {
			var pizzaIngredientUpdatedModel = pizzaIngredientModelAssembler.toModel(pizzaIngredientRepositoryService.save(pizzaIngredient));
			return ResponseEntity.created(pizzaIngredientUpdatedModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
				.body(pizzaIngredientUpdatedModel);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Insert error");
		}
	}

	@PutMapping("/pizza_ingredient/{id}")
	public ResponseEntity<?> update(@RequestBody PizzaIngredient pizzaIngredient, @PathVariable Long id){
		pizzaIngredientRepositoryService.findById(id)
			.orElseThrow(() -> new PizzaIngredientNotFoundException(id));
		try {
			var pizzaIngredientUpdatedModel = pizzaIngredientModelAssembler.toModel(pizzaIngredientRepositoryService.save(pizzaIngredient));
			return ResponseEntity.created(pizzaIngredientUpdatedModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
				.body(pizzaIngredientUpdatedModel);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Update error");
		}
	}

	@DeleteMapping("/pizza_ingredient/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		pizzaIngredientRepositoryService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
