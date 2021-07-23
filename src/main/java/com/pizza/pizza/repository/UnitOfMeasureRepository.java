package com.pizza.pizza.repository;

import java.util.Optional;

import com.pizza.pizza.model.UnitOfMeasure;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UnitOfMeasureRepository extends JpaRepository<UnitOfMeasure, Long>{
    Optional<UnitOfMeasure> findByName(String name);    
}
