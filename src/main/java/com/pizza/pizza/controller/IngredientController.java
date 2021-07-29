package com.pizza.pizza.controller;

import java.util.stream.Collectors;

import com.pizza.pizza.assembler.IngredientModelAssembler;
import com.pizza.pizza.exception.IngredientNotFoundException;
import com.pizza.pizza.model.Ingredient;
import com.pizza.pizza.service.IngredientRepositoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class IngredientController {
    
	@Autowired private IngredientRepositoryService ingredientRepositoryService;
	@Autowired private IngredientModelAssembler ingredientModelAssembler;
    
	@GetMapping("/ingredients")
	public CollectionModel<EntityModel<Ingredient>> getIngredients() {
		var ingredients = ingredientRepositoryService.findAll();
		return CollectionModel.of(ingredients.stream().map(ingredientModelAssembler::toModel).collect(Collectors.toList()));
	}

	@GetMapping("/ingredients/{id}")
	public EntityModel<Ingredient> getIngredient(@PathVariable Long id) {
		var ingredient = ingredientRepositoryService.findById(id);
        return ingredientModelAssembler.toModel(ingredient.orElseThrow(() -> new IngredientNotFoundException(id)));
	}

	@GetMapping("/ingredients/name/{name}")
	public EntityModel<Ingredient> getIngredient(@PathVariable String name) {
		var ingredient = ingredientRepositoryService.findByName(name);
		return ingredientModelAssembler.toModel(ingredient.orElseThrow(() -> new IngredientNotFoundException(name)));
	}

	@PostMapping("/ingredients")
	public ResponseEntity<?> create(@RequestBody Ingredient ingredient){
		try {
			var ingredientUpdatedModel = ingredientModelAssembler.toModel(ingredientRepositoryService.save(ingredient));
			return ResponseEntity.created(ingredientUpdatedModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
				.body(ingredientUpdatedModel);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Insert error");
		}
	}

	@PutMapping("/ingredients/{id}")
	public ResponseEntity<?> update(@RequestBody Ingredient ingredient, @PathVariable Long id){
		ingredientRepositoryService.findById(id)
			.orElseThrow(() -> new IngredientNotFoundException(id));
		try {
			var ingredientUpdatedModel = ingredientModelAssembler.toModel(ingredientRepositoryService.save(ingredient));
			return ResponseEntity.created(ingredientUpdatedModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
				.body(ingredientUpdatedModel);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Update error");
		}
	}

	@DeleteMapping("/ingredients/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		ingredientRepositoryService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
