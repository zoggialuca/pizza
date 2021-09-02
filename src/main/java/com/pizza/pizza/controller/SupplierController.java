package com.pizza.pizza.controller;

import com.pizza.pizza.dto.SupplierDTO;
import com.pizza.pizza.service.SupplierService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

}
