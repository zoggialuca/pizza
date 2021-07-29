package com.pizza.pizza.controller;

import java.util.stream.Collectors;

import com.pizza.pizza.assembler.UnitOfMeasureModelAssembler;
import com.pizza.pizza.exception.UnitOfMeasureNotFoundException;
import com.pizza.pizza.model.UnitOfMeasure;
import com.pizza.pizza.service.UnitOfMeasureRepositoryService;

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
public class UnitOfMeasureController {
    
	@Autowired private UnitOfMeasureRepositoryService unitOfMeasureRepositoryService;
	@Autowired private UnitOfMeasureModelAssembler unitOfMeasureModelAssembler;
    
	@GetMapping("/units_of_measure")
	public CollectionModel<EntityModel<UnitOfMeasure>> getUnitOfMeasures() {
		var unitOfMeasures = unitOfMeasureRepositoryService.findAll();
		return CollectionModel.of(unitOfMeasures.stream().map(unitOfMeasureModelAssembler::toModel).collect(Collectors.toList()));
	}

	@GetMapping("/units_of_measure/{id}")
	public EntityModel<UnitOfMeasure> getUnitOfMeasure(@PathVariable Long id) {
		var unitOfMeasure = unitOfMeasureRepositoryService.findById(id);
        return unitOfMeasureModelAssembler.toModel(unitOfMeasure.orElseThrow(() -> new UnitOfMeasureNotFoundException(id)));
	}

	@GetMapping("/units_of_measure/name/{name}")
	public EntityModel<UnitOfMeasure> getUnitOfMeasure(@PathVariable String name) {
		var unitOfMeasure = unitOfMeasureRepositoryService.findByName(name);
		return unitOfMeasureModelAssembler.toModel(unitOfMeasure.orElseThrow(() -> new UnitOfMeasureNotFoundException(name)));
	}

	@PostMapping("/units_of_measure")
	public ResponseEntity<?> create(@RequestBody UnitOfMeasure unitOfMeasure){
		try {
			var unitOfMeasureUpdatedModel = unitOfMeasureModelAssembler.toModel(unitOfMeasureRepositoryService.save(unitOfMeasure));
			return ResponseEntity.created(unitOfMeasureUpdatedModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
				.body(unitOfMeasureUpdatedModel);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Insert error");
		}
	}

	@PutMapping("/units_of_measure/{id}")
	public ResponseEntity<?> update(@RequestBody UnitOfMeasure unitOfMeasure, @PathVariable Long id){
		unitOfMeasureRepositoryService.findById(id)
			.orElseThrow(() -> new UnitOfMeasureNotFoundException(id));
		try {
			var unitOfMeasureUpdatedModel = unitOfMeasureModelAssembler.toModel(unitOfMeasureRepositoryService.save(unitOfMeasure));
			return ResponseEntity.created(unitOfMeasureUpdatedModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
				.body(unitOfMeasureUpdatedModel);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Update error");
		}
	}

	@DeleteMapping("/units_of_measure/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		unitOfMeasureRepositoryService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
