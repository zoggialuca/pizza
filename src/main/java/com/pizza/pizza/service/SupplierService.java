package com.pizza.pizza.service;

import com.pizza.pizza.converter.EntityDTOBidirectionalConverter;
import com.pizza.pizza.dto.IngredientDTO;
import com.pizza.pizza.dto.SupplierDTO;
import com.pizza.pizza.dto.SupplierUpdateRequestDTO;
import com.pizza.pizza.exception.IngredientAlreadySuppliedException;
import com.pizza.pizza.exception.IngredientNotFoundException;
import com.pizza.pizza.exception.SupplierNameAlreadyExistsException;
import com.pizza.pizza.exception.SupplierNotFoundException;
import com.pizza.pizza.model.Ingredient;
import com.pizza.pizza.model.IngredientSupplier;
import com.pizza.pizza.model.Supplier;
import com.pizza.pizza.repository.IngredientSupplierRepository;
import com.pizza.pizza.repository.SupplierRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SupplierService {

  private final SupplierRepository supplierRepository;
  private final IngredientSupplierRepository ingredientSupplierRepository;
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
    var possibleIngredientSupplierToAdd = prepareIngredientsToAdd(ingredients, supplier);
    if (possibleIngredientSupplierToAdd.isEmpty()) {
      throw new IngredientAlreadySuppliedException(ingredients.stream().map(Ingredient::getId).collect(
          Collectors.toList()));
    }

    supplier.getIngredients().addAll(possibleIngredientSupplierToAdd);
    supplierRepository.save(supplier);
  }

  private List<IngredientSupplier> prepareIngredientsToAdd(List<Ingredient> ingredients, Supplier supplier) {
    List<Long> ingredientIds = ingredients.stream().map(Ingredient::getId).collect(Collectors.toList());
    List<Long> ingredientsAlreadySupplied = ingredientSupplierRepository.findAllByIngredientIdIn(ingredientIds)
        .stream()
        .map(ingredientSupplier -> ingredientSupplier.getIngredient().getId())
        .collect(Collectors.toList());

    return ingredients.stream()
        .filter(ingredient -> !ingredientsAlreadySupplied.contains(ingredient.getId()))
        .map(ingredient -> new IngredientSupplier(supplier, ingredient))
        .collect(Collectors.toList());
  }

  public List<IngredientDTO> getIngredientsForSupplier(Long supplierId) {
    return ingredientsFinder.findBySupplierId(supplierId);
  }

  public SupplierDTO updateSupplier(Long id, SupplierUpdateRequestDTO supplierDTO) {

    supplierRepository.findByName(supplierDTO.getName())
        .ifPresent(supplier -> {
          throw new SupplierNameAlreadyExistsException(supplier.getName());
        });

    return supplierRepository.findById(id).map(supplier -> {
          supplier.setName(supplierDTO.getName());
          return supplierConverter.toDTO(supplierRepository.save(supplier));
        })
        .orElseThrow(() -> new SupplierNotFoundException(id));
  }

  public void deleteSupplier(Long id) {
    supplierRepository.deleteById(id);
  }
}
