package com.pizza.pizza.repository;

import com.pizza.pizza.model.UnitOfMeasure;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnitOfMeasureRepository extends JpaRepository<UnitOfMeasure, Long> {

  Optional<UnitOfMeasure> findByName(String name);
}
