package com.pizza.pizza;

import lombok.Getter;

@Getter
public class Pizza {
    public String name;

    public Pizza(String name) {
        this.name = name;
    }
}
