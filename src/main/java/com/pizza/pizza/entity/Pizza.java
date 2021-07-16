package com.pizza.pizza.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.NonNull;

@Entity
@Data
public class Pizza {
    @Id
    private final @NonNull String name;

    //necessary to run the demo
    protected Pizza(){
        this.name = "";
    }

    //necessary to run the demo
    public Pizza(String name){
        this.name = name;
    }
}
