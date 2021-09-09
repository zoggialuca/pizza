package com.pizza.pizza.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.pizza.pizza.controller.PizzaController;
import com.pizza.pizza.dto.PizzaDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class PizzaModelAssembler implements RepresentationModelAssembler<PizzaDTO, EntityModel<PizzaDTO>> {

  @Override
  public EntityModel<PizzaDTO> toModel(PizzaDTO pizza) {
    return EntityModel.of(pizza,
        linkTo(methodOn(PizzaController.class).getPizza(pizza.getId())).withSelfRel(),
        linkTo(methodOn(PizzaController.class).getPizzas()).withRel("pizzas"),
        linkTo(methodOn(PizzaController.class).getPizzaIngredients(pizza.getId())).withRel("ingredient")
    );
  }
}
