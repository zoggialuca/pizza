package com.pizza.pizza.converter;

import com.pizza.pizza.dto.UnitOfMeasureDTO;
import com.pizza.pizza.model.UnitOfMeasure;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureEntityDTOBidirectionalConverter implements EntityDTOBidirectionalConverter<UnitOfMeasure, UnitOfMeasureDTO> {

  @Override
  public UnitOfMeasureDTO toDTO(UnitOfMeasure unitOfMeasure) {
    if (unitOfMeasure == null) {
      return null;
    }

    return UnitOfMeasureDTO.builder()
                           .id(unitOfMeasure.getId())
                           .name(unitOfMeasure.getName()).build();
  }

  @Override
  public Optional<UnitOfMeasure> toOptionalEntity(UnitOfMeasureDTO unitOfMeasureDTO) {
    if (unitOfMeasureDTO == null) {
      return Optional.empty();
    }

    return Optional.of(UnitOfMeasure.builder()
                                    .id(unitOfMeasureDTO.getId())
                                    .name(unitOfMeasureDTO.getName())
                                    .build());
  }
}
