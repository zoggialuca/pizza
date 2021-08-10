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
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Table(name="pizza"
    , uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})}
)
public class Pizza extends RepresentationModel<Pizza>{
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private final @NonNull String name;
    private final @NonNull Boolean isVegetarian;

    @OneToMany(mappedBy = "pizza") @ToString.Exclude @EqualsAndHashCode.Exclude
    @JsonIgnore
    Set<PizzaIngredient> pizzaIngredients;

    //necessary to run the demo
    protected Pizza(){
        this("");
    }

    //necessary to run the demo
    public Pizza(String name){
        this(name, Boolean.FALSE);
    }

    //necessary to run the demo
    public Pizza(String name, Boolean isVegetarian){
        this.name = name;
        this.isVegetarian = isVegetarian;
    }

    //implement: equals and hasCode
}
