package com.pizza.pizza.service;

import com.pizza.pizza.converter.EntityToDTOConverter;
import com.pizza.pizza.dto.SupplierDTO;
import com.pizza.pizza.model.Supplier;
import com.pizza.pizza.repository.SupplierRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SupplierService {
  private final SupplierRepository supplierRepository;
  private final EntityToDTOConverter<Supplier, SupplierDTO> converter;

  public List<SupplierDTO> getAllSuppliers() {
    return supplierRepository.findAll().stream().map(converter::toDTO).collect(Collectors.toList());
  }

  public Optional<SupplierDTO> getSupplier(Long id) {
    return supplierRepository.findById(id).map(converter::toDTO);
  }
}
