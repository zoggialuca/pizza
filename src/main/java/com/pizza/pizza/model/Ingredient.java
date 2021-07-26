package com.pizza.pizza.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

@Entity
@Data
@Table(name="ingredient"
    , uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})}
)
public class Ingredient extends RepresentationModel<Ingredient>{
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private final @NonNull String name;

    @OneToMany(mappedBy = "ingredient") @ToString.Exclude @EqualsAndHashCode.Exclude
    @JsonIgnore
    Set<PizzaIngredient> pizzaIngredients;
    
    //necessary to run the demo
    protected Ingredient(){
        this("");
    }

    //necessary to run the demo
    public Ingredient(String name){
        this.name = name;
    }
}
