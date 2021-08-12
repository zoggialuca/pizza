package com.pizza.pizza.converter;

import com.pizza.pizza.dto.UnitOfMeasureDTO;
import com.pizza.pizza.model.UnitOfMeasure;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UnitOfMeasureEntityDTOBidirectionalConverter implements EntityDTOBidirectionalConverter<UnitOfMeasure, UnitOfMeasureDTO> {
    @Override
    public UnitOfMeasureDTO toDTO(UnitOfMeasure unitOfMeasure) {
        if (unitOfMeasure == null) {
            return null;
        }

        var unitOfMeasureDTO = new UnitOfMeasureDTO();
        unitOfMeasureDTO.setId(unitOfMeasure.getId());
        unitOfMeasureDTO.setName(unitOfMeasure.getName());
        return unitOfMeasureDTO;
    }

    @Override
    public Optional<UnitOfMeasure> toOptionalEntity(UnitOfMeasureDTO unitOfMeasureDTO) {
        if (unitOfMeasureDTO == null){
            return Optional.empty();
        }

        var unitOfMeasure = new UnitOfMeasure(unitOfMeasureDTO.getName());
        unitOfMeasure.setId(unitOfMeasureDTO.getId());
        return Optional.of(unitOfMeasure);
    }
}
