package com.pizza.pizza.repository;

import com.pizza.pizza.model.Pizza;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, String>{
    Pizza findByName(String name);
}