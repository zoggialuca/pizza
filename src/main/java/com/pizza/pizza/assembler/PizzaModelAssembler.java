package com.pizza.pizza.assembler;

import com.pizza.pizza.controller.PizzaController;
import com.pizza.pizza.model.Pizza;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class PizzaModelAssembler implements RepresentationModelAssembler<Pizza, EntityModel<Pizza>> {
    
    @Override
    public EntityModel<Pizza> toModel(Pizza pizza){
        return EntityModel.of(pizza
            , linkTo(methodOn(PizzaController.class).getPizza(pizza.getId())).withSelfRel()
            , linkTo(methodOn(PizzaController.class).getPizzas()).withRel("pizzas")
            );
    }
}
