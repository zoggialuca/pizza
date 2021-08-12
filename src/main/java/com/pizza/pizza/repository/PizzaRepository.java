package com.pizza.pizza.repository;

import com.pizza.pizza.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PizzaRepository extends JpaRepository<Pizza, Long>{
    Optional<Pizza> findByName(String name);
    List<Pizza> findByIsVegetarian(boolean isVegetarian);
}