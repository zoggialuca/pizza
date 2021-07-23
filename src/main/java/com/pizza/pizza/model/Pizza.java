package com.pizza.pizza.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pizza.pizza.serializer.PizzaIngredientIngredientsSerializer;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

@Entity
@Data
@Table(name="pizza"
    , uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})}
)
public class Pizza {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private final @NonNull String name;
    private final @NonNull Boolean isVegetarian;

    @OneToMany(mappedBy = "pizza") @ToString.Exclude @EqualsAndHashCode.Exclude
    @JsonSerialize(using = PizzaIngredientIngredientsSerializer.class)
    Set<PizzaIngredient> pizzaIngredients;

    //necessary to run the demo
    protected Pizza(){
        this.name = "";
        this.isVegetarian = false;
    }

    //necessary to run the demo
    public Pizza(String name){
        this.name = name;
        this.isVegetarian = false;
    }

    //necessary to run the demo
    public Pizza(String name, Boolean isVegetarian){
        this.name = name;
        this.isVegetarian = isVegetarian;
    }
}
