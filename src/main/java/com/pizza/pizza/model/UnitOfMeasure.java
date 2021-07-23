package com.pizza.pizza.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NonNull;

@Entity
@Data
@Table(name="unit_of_measure")
public class UnitOfMeasure {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private final @NonNull String name;
    
    //necessary to run the demo
    protected UnitOfMeasure(){
        this.name = "";
    }

    //necessary to run the demo
    public UnitOfMeasure(String name){
        this.name = name;
    }
}
