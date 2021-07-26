package com.pizza.pizza.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;
import lombok.NonNull;

@Entity
@Data
@Table(name="pizza_ingredient"
    , uniqueConstraints = {@UniqueConstraint(columnNames = {"pizza", "ingredient"})}    
)
public class PizzaIngredient extends RepresentationModel<PizzaIngredient>{
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pizza")
    private final @NonNull Pizza pizza;

    @ManyToOne
    @JoinColumn(name = "ingredient")
    private final @NonNull Ingredient ingredient;

    private Double quantity;

    @ManyToOne(targetEntity = UnitOfMeasure.class)
    @JoinColumn(name = "unit_of_measure")
    private UnitOfMeasure unitOfMeasure;

    //necessary to run the demo
    protected PizzaIngredient(){
        this(null, null);
    }

    //necessary to run the demo
    public PizzaIngredient(Pizza pizza, Ingredient ingredient){
        this.pizza = pizza;
        this.ingredient = ingredient;
    }
}
