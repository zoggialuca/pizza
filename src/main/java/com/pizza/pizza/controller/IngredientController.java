package com.pizza.pizza.controller;

import com.pizza.pizza.assembler.IngredientModelAssembler;
import com.pizza.pizza.dto.IngredientDTO;
import com.pizza.pizza.service.IngredientService;
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
public class IngredientController {

    private final IngredientService ingredientService;
    private final IngredientModelAssembler ingredientModelAssembler;

    @GetMapping("/ingredients")
    public ResponseEntity<CollectionModel<EntityModel<IngredientDTO>>> getIngredients() {
        var ingredients = ingredientService.findAll();
        return ResponseEntity.ok(CollectionModel.of(ingredients.stream().map(ingredientModelAssembler::toModel).collect(Collectors.toList())));
    }

    @GetMapping("/ingredients/{id}")
    public ResponseEntity<EntityModel<IngredientDTO>> getIngredient(@PathVariable Long id) {
        return ResponseEntity.ok(ingredientModelAssembler.toModel(ingredientService.findById(id)));
    }

    @GetMapping(path = "/ingredients", params = {"name"})
    public ResponseEntity<EntityModel<IngredientDTO>> getIngredient(@RequestParam String name) {
        return ResponseEntity.ok(ingredientModelAssembler.toModel(ingredientService.findByName(name)));
    }

    @PostMapping("/ingredients")
    public ResponseEntity<EntityModel<IngredientDTO>> create(@RequestBody IngredientDTO ingredientDTO) {
        var entityModel = ingredientModelAssembler.toModel(ingredientService.create(ingredientDTO));
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PutMapping("/ingredients/{id}")
    public ResponseEntity<EntityModel<IngredientDTO>> update(@RequestBody IngredientDTO ingredientDTO, @PathVariable Long id) {
        var entityModel = ingredientModelAssembler.toModel(ingredientService.update(ingredientDTO, id));
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/ingredients/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ingredientService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
