package com.pizza.pizza.converter;

import com.pizza.pizza.dto.SupplierDTO;
import com.pizza.pizza.model.Supplier;
import org.springframework.stereotype.Component;

@Component
public class SupplierEntityToDTOConverter implements EntityToDTOConverter<Supplier, SupplierDTO>{

  @Override
  public SupplierDTO toDTO(Supplier entity) {
    SupplierDTO supplierDTO = new SupplierDTO();
    supplierDTO.setId(entity.getId());
    supplierDTO.setName(entity.getName());
    supplierDTO.setEmail(entity.getEmail());
    supplierDTO.setAddress(entity.getAddress());

    return supplierDTO;
  }
}
