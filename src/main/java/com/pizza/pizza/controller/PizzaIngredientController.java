package com.pizza.pizza.controller;

import com.pizza.pizza.assembler.PizzaIngredientModelAssembler;
import com.pizza.pizza.dto.PizzaIngredientRequestDTO;
import com.pizza.pizza.dto.PizzaIngredientResponseDTO;
import com.pizza.pizza.service.PizzaIngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PizzaIngredientController {

  private final PizzaIngredientService pizzaIngredientService;
  private final PizzaIngredientModelAssembler pizzaIngredientModelAssembler;

  @GetMapping("/pizzas_ingredients/{id}")
  public ResponseEntity<EntityModel<PizzaIngredientResponseDTO>> getPizzaIngredient(@PathVariable Long id) {
    return ResponseEntity.ok(pizzaIngredientModelAssembler.toModel(pizzaIngredientService.findById(id)));
  }

  @PostMapping("/pizzas_ingredients")
  public ResponseEntity<EntityModel<PizzaIngredientResponseDTO>> create(@RequestBody PizzaIngredientRequestDTO pizzaIngredientRequestDTO) {
    var entityModel = pizzaIngredientModelAssembler.toModel(pizzaIngredientService.create(pizzaIngredientRequestDTO));
    return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                         .body(entityModel);
  }

  @PutMapping("/pizzas_ingredients/{id}")
  public ResponseEntity<EntityModel<PizzaIngredientResponseDTO>> update(@RequestBody PizzaIngredientRequestDTO pizzaIngredientRequestDTO,
      @PathVariable Long id) {
    var entityModel = pizzaIngredientModelAssembler.toModel(pizzaIngredientService.update(pizzaIngredientRequestDTO, id));
    return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                         .body(entityModel);
  }

  @DeleteMapping("/pizzas_ingredients/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    pizzaIngredientService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
