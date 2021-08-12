package com.pizza.pizza.assembler;

import com.pizza.pizza.controller.UnitOfMeasureController;
import com.pizza.pizza.dto.UnitOfMeasureDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UnitOfMeasureModelAssembler implements RepresentationModelAssembler<UnitOfMeasureDTO, EntityModel<UnitOfMeasureDTO>> {
    
    @Override
    public EntityModel<UnitOfMeasureDTO> toModel(UnitOfMeasureDTO unitOfMeasureDTO){
        return EntityModel.of(unitOfMeasureDTO
            , linkTo(methodOn(UnitOfMeasureController.class).getUnitOfMeasure(unitOfMeasureDTO.getId())).withSelfRel()
            , linkTo(methodOn(UnitOfMeasureController.class).getUnitsOfMeasures()).withRel("unitOfMeasures")
            );
    }
}
