package com.pizza.pizza.entity;

import org.springframework.data.repository.CrudRepository;

public interface PizzaRepository extends CrudRepository<Pizza, String>{
    Pizza findByName(String name);
}