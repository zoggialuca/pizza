package com.pizza.pizza.controller;

import com.pizza.pizza.assembler.UnitOfMeasureModelAssembler;
import com.pizza.pizza.dto.UnitOfMeasureDTO;
import com.pizza.pizza.service.UnitOfMeasureService;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UnitOfMeasureController {

  private final UnitOfMeasureModelAssembler unitOfMeasureModelAssembler;
  private final UnitOfMeasureService unitOfMeasureService;

  @GetMapping("/units_of_measure")
  public ResponseEntity<CollectionModel<EntityModel<UnitOfMeasureDTO>>> getUnitsOfMeasures() {
    var unitOfMeasures = unitOfMeasureService.findAll();
    return ResponseEntity.ok(CollectionModel.of(unitOfMeasures.stream().map(unitOfMeasureModelAssembler::toModel).collect(Collectors.toList())));
  }

  @GetMapping("/units_of_measure/{id}")
  public ResponseEntity<EntityModel<UnitOfMeasureDTO>> getUnitOfMeasure(@PathVariable Long id) {
    return ResponseEntity.ok(unitOfMeasureModelAssembler.toModel(unitOfMeasureService.findById(id)));
  }

  @GetMapping(path = "/units_of_measure", params = { "name" })
  public ResponseEntity<EntityModel<UnitOfMeasureDTO>> getUnitOfMeasure(@RequestParam String name) {
    return ResponseEntity.ok(unitOfMeasureModelAssembler.toModel(unitOfMeasureService.findByName(name)));
  }

  @PostMapping("/units_of_measure")
  public ResponseEntity<EntityModel<UnitOfMeasureDTO>> create(@RequestBody @Valid UnitOfMeasureDTO unitOfMeasureDTO) {
    var entityModel = unitOfMeasureModelAssembler.toModel(unitOfMeasureService.create(unitOfMeasureDTO));
    return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
  }

  @PutMapping("/units_of_measure/{id}")
  public ResponseEntity<EntityModel<UnitOfMeasureDTO>> update(@RequestBody @Valid UnitOfMeasureDTO unitOfMeasureDTO, @PathVariable Long id) {
    var entityModel = unitOfMeasureModelAssembler.toModel(unitOfMeasureService.update(unitOfMeasureDTO, id));
    return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
  }

  @DeleteMapping("/units_of_measure/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    unitOfMeasureService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
