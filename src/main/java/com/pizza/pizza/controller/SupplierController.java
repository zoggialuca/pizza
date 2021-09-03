package com.pizza.pizza.controller;

import com.pizza.pizza.dto.IngredientDTO;
import com.pizza.pizza.dto.SupplierDTO;
import com.pizza.pizza.dto.SupplierUpdateRequestDTO;
import com.pizza.pizza.service.SupplierService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("suppliers")
@RequiredArgsConstructor
public class SupplierController {

  private final SupplierService supplierService;

  @GetMapping
  public ResponseEntity<List<SupplierDTO>> getSuppliers() {
    return ResponseEntity.ok(supplierService.getAllSuppliers());
  }

  @GetMapping("{id}")
  public ResponseEntity<SupplierDTO> getSupplier(@PathVariable Long id) {

    return supplierService.getSupplier(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<SupplierDTO> createSupplier(SupplierDTO supplierDTO) {
    return supplierService.createSupplier(supplierDTO)
        .map(supplier -> ResponseEntity.status(HttpStatus.CREATED).body(supplier))
        .orElseGet(() -> ResponseEntity.badRequest().build());
  }

  @PutMapping("{id}")
  public ResponseEntity<SupplierDTO> updateSupplier(@PathVariable Long id, @RequestBody @Valid SupplierUpdateRequestDTO supplierDTO) {
    return ResponseEntity.ok(supplierService.updateSupplier(id, supplierDTO));
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> deleteSupplier(@PathVariable Long id) {
    supplierService.deleteSupplier(id);
    return ResponseEntity.noContent().build();
  }


  @PostMapping("{id}/ingredients")
  public ResponseEntity<Void> addIngredientsForSupplier(@PathVariable Long supplierId, @RequestBody List<Long> ingredientIds) {
    supplierService.addIngredientsForSupplier(supplierId, ingredientIds);
    return ResponseEntity.ok().build();
  }

  @GetMapping("{id}/ingredients")
  public ResponseEntity<List<IngredientDTO>> getIngredientsForSupplier(@PathVariable Long supplierId) {
    return ResponseEntity.ok(supplierService.getIngredientsForSupplier(supplierId));
  }

}
