package com.pizza.pizza.assembler;

import com.pizza.pizza.controller.UnitOfMeasureController;
import com.pizza.pizza.model.UnitOfMeasure;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class UnitOfMeasureModelAssembler implements RepresentationModelAssembler<UnitOfMeasure, EntityModel<UnitOfMeasure>> {
    
    @Override
    public EntityModel<UnitOfMeasure> toModel(UnitOfMeasure unitOfMeasure){
        return EntityModel.of(unitOfMeasure
            , linkTo(methodOn(UnitOfMeasureController.class).getUnitOfMeasure(unitOfMeasure.getId())).withSelfRel()
            , linkTo(methodOn(UnitOfMeasureController.class).getUnitOfMeasures()).withRel("unitOfMeasures")
            );
    }
}
