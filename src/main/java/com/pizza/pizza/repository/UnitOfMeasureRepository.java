package com.pizza.pizza.repository;

import com.pizza.pizza.model.UnitOfMeasure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UnitOfMeasureRepository extends JpaRepository<UnitOfMeasure, Long>{
    Optional<UnitOfMeasure> findByName(String name);    
}
