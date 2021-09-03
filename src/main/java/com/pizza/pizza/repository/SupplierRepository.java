package com.pizza.pizza.repository;

import com.pizza.pizza.model.Supplier;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

  Optional<Supplier> findByName(String name);
}
