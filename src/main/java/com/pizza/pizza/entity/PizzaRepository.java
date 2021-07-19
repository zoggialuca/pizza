package com.pizza.pizza.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, String>{
    Pizza findByName(String name);
}