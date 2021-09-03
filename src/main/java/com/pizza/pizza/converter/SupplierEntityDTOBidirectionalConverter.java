package com.pizza.pizza.converter;

import com.pizza.pizza.dto.SupplierDTO;
import com.pizza.pizza.model.Supplier;

import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class SupplierEntityDTOBidirectionalConverter implements EntityDTOBidirectionalConverter<Supplier, SupplierDTO>{
    @Override
    public Optional<Supplier> toOptionalEntity(SupplierDTO dto) {
        if (dto == null) {
            return Optional.empty();
        }
        var supplier = new Supplier(dto.getEmail(), dto.getName(), dto.getAddress());
        return Optional.of(supplier);
    }

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
