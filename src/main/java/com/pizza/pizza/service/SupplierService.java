package com.pizza.pizza.service;

import com.pizza.pizza.converter.EntityDTOBidirectionalConverter;
import com.pizza.pizza.dto.IngredientDTO;
import com.pizza.pizza.dto.SupplierDTO;
import com.pizza.pizza.exception.IngredientNotFoundException;
import com.pizza.pizza.exception.SupplierNotFoundException;
import com.pizza.pizza.model.Ingredient;
import com.pizza.pizza.model.Supplier;
import com.pizza.pizza.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SupplierService {
    private final SupplierRepository supplierRepository;
    private final IngredientsFinder ingredientsFinder;
    private final EntityDTOBidirectionalConverter<Supplier, SupplierDTO> supplierConverter;


    public List<SupplierDTO> getAllSuppliers() {
        return supplierRepository.findAll().stream().map(supplierConverter::toDTO).collect(Collectors.toList());
    }

    public Optional<SupplierDTO> getSupplier(Long id) {
        return supplierRepository.findById(id).map(supplierConverter::toDTO);
    }

    public Optional<SupplierDTO> createSupplier(SupplierDTO supplierDTO) {
        var supplier = supplierConverter.toOptionalEntity(supplierDTO);
        return supplier.map(supplierRepository::save).map(supplierConverter::toDTO);
    }

    public void addIngredientsForSupplier(Long supplierId, List<Long> ingredientIds) {
        var supplier = supplierRepository.findById(supplierId).orElseThrow(() -> new SupplierNotFoundException(supplierId));
        List<Ingredient> ingredients = ingredientsFinder.findByIdIn(ingredientIds);
        if (ingredients.isEmpty()) {
            throw new IngredientNotFoundException(ingredientIds);
        }
        supplier.getIngredients().addAll(ingredients);
        supplierRepository.save(supplier);
    }

    public List<IngredientDTO> getIngredientsForSupplier(Long supplierId) {
        return ingredientsFinder.findBySupplierId(supplierId);
    }
}
