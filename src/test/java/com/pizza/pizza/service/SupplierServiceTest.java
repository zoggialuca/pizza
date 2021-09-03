package com.pizza.pizza.service;

import com.pizza.pizza.converter.EntityDTOBidirectionalConverter;
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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SupplierServiceTest {

    private static final Supplier SUPPLIER = new Supplier("name", "name", "adress");

    private static final List<Long> INGREDIENT_IDS = List.of(1L, 2L);

    private static final Ingredient INGREDIENT = new Ingredient("funghi");

    private static final Long SUPPLIER_ID = 1L;

    static {
        INGREDIENT.setId(1L);
        SUPPLIER.setIngredients(new ArrayList<>());
    }

    @Mock
    private SupplierRepository supplierRepository;
    @Mock
    private IngredientSupplierRepository ingredientSupplierRepository;
    @Mock
    private IngredientsFinder ingredientsFinder;
    @Mock
    private EntityDTOBidirectionalConverter<Supplier, SupplierDTO> supplierConverter;

    private SupplierService supplierService;


    @BeforeEach
    void setup() {
        supplierService = new SupplierService(supplierRepository, ingredientSupplierRepository, ingredientsFinder, supplierConverter);
    }


    @Test
    void shouldThrowAnExceptionBecauseOfMissingSupplier() {
        when(supplierRepository.findById(SUPPLIER_ID)).thenReturn(Optional.empty());
        assertThatExceptionOfType(SupplierNotFoundException.class).isThrownBy(() -> supplierService.addIngredientsForSupplier(SUPPLIER_ID, List.of()));
    }

    @Test
    void shouldThrowAnExceptionBecauseOfEmptyIngredientsList() {
        when(supplierRepository.findById(SUPPLIER_ID)).thenReturn(Optional.of(SUPPLIER));
        when(ingredientsFinder.findByIdIn(INGREDIENT_IDS)).thenReturn(List.of());
        assertThatExceptionOfType(IngredientNotFoundException.class).isThrownBy(() -> supplierService.addIngredientsForSupplier(SUPPLIER_ID, INGREDIENT_IDS));
    }

    @Test
    void shouldThrowAnExceptionIfAllIngredientsAreSupplied() {
        when(supplierRepository.findById(SUPPLIER_ID)).thenReturn(Optional.of(SUPPLIER));
        var ingredientSupplier = new IngredientSupplier(SUPPLIER, INGREDIENT);
        when(ingredientsFinder.findByIdIn(INGREDIENT_IDS)).thenReturn(List.of(INGREDIENT));
        when(ingredientSupplierRepository.findAllByIngredientIdIn(List.of(INGREDIENT.getId()))).thenReturn(List.of(ingredientSupplier));
        assertThatExceptionOfType(IngredientAlreadySuppliedException.class).isThrownBy(() -> supplierService.addIngredientsForSupplier(SUPPLIER_ID, INGREDIENT_IDS));
    }

    @Test
    void shouldSuccessfullyAddIngredientForSupplier() {
        when(supplierRepository.findById(SUPPLIER_ID)).thenReturn(Optional.of(SUPPLIER));
        when(ingredientsFinder.findByIdIn(INGREDIENT_IDS)).thenReturn(List.of(INGREDIENT));
        when(ingredientSupplierRepository.findAllByIngredientIdIn(List.of(INGREDIENT.getId()))).thenReturn(List.of());
        supplierService.addIngredientsForSupplier(SUPPLIER_ID, INGREDIENT_IDS);
        verify(supplierRepository).save(SUPPLIER);
    }

    @Test
    void shouldThrowSupplierNameAlreadyExistsExceptionForUpdate() {
        var supplierDTORequest = new SupplierUpdateRequestDTO();
        supplierDTORequest.setName("name");
        when(supplierRepository.findByName(supplierDTORequest.getName())).thenReturn(Optional.of(SUPPLIER));
        assertThatExceptionOfType(SupplierNameAlreadyExistsException.class).isThrownBy(() -> supplierService.updateSupplier(SUPPLIER_ID, supplierDTORequest));
    }


    @Test
    void shouldThrowSupplierNotFoundExceptionForUpdate() {
        var supplierDTORequest = new SupplierUpdateRequestDTO();
        when(supplierRepository.findById(SUPPLIER_ID)).thenReturn(Optional.empty());
        assertThatExceptionOfType(SupplierNotFoundException.class).isThrownBy(() -> supplierService.updateSupplier(SUPPLIER_ID, supplierDTORequest));
    }

    @Test
    void shouldUpdateSupplier() {
        var supplierDTORequest = new SupplierUpdateRequestDTO();
        supplierDTORequest.setName("name");
        var supplierDTO = new SupplierDTO();
        when(supplierRepository.findByName(supplierDTORequest.getName())).thenReturn(Optional.empty());
        when(supplierRepository.findById(SUPPLIER_ID)).thenReturn(Optional.of(SUPPLIER));
        when(supplierConverter.toDTO(SUPPLIER)).thenReturn(supplierDTO);
        when(supplierRepository.save(SUPPLIER)).thenReturn(SUPPLIER);
        supplierService.updateSupplier(SUPPLIER_ID, supplierDTORequest);
        verify(supplierRepository).save(SUPPLIER);
    }


}
