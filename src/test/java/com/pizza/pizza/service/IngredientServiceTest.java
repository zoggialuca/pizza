package com.pizza.pizza.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.pizza.pizza.converter.EntityDTOBidirectionalConverter;
import com.pizza.pizza.dto.IngredientDTO;
import com.pizza.pizza.exception.IngredientAlreadyExistsException;
import com.pizza.pizza.exception.IngredientNotFoundException;
import com.pizza.pizza.model.Ingredient;
import com.pizza.pizza.repository.IngredientRepository;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class IngredientServiceTest {

  private static final String INGREDIENT_NAME = "Salami";
  private static final Long INGREDIENT_ID = 1L;
  private static final String NOTES = "Note";

  @Mock
  private EntityDTOBidirectionalConverter<Ingredient, IngredientDTO> converter;
  @Mock
  private IngredientRepository ingredientRepository;

  private IngredientService ingredientService;

  @BeforeEach
  public void setUp() {
    ingredientService = new IngredientService(converter, ingredientRepository);
  }

  @Test
  public void shouldThrowIngredientAlreadyExistsException() {
    IngredientDTO ingredientDTO = new IngredientDTO();
    ingredientDTO.setName(INGREDIENT_NAME);
    when(ingredientRepository.findByName(INGREDIENT_NAME)).thenReturn(Optional.of(new Ingredient(INGREDIENT_NAME)));
    assertThatExceptionOfType(IngredientAlreadyExistsException.class).isThrownBy(() -> ingredientService.update(ingredientDTO, INGREDIENT_ID));
  }

  @Test
  public void shouldThrowIngredientNotFoundException() {
    IngredientDTO ingredientDTO = new IngredientDTO();
    ingredientDTO.setName(INGREDIENT_NAME);
    when(ingredientRepository.findByName(INGREDIENT_NAME)).thenReturn(Optional.empty());

    when(ingredientRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
    assertThatExceptionOfType(IngredientNotFoundException.class).isThrownBy(() -> ingredientService.update(ingredientDTO, INGREDIENT_ID));
  }

  @Test
  public void shouldReturnIngredientDTO() {
    Ingredient ingredient = Mockito.mock(Ingredient.class);

    IngredientDTO ingredientDTO = new IngredientDTO();
    ingredientDTO.setName(INGREDIENT_NAME);
    ingredientDTO.setId(INGREDIENT_ID);
    ingredientDTO.setNotes(NOTES);

    when(ingredientRepository.findByName(INGREDIENT_NAME)).thenReturn(Optional.empty());
    when(ingredientRepository.findById(INGREDIENT_ID)).thenReturn(Optional.of(ingredient));
    when(ingredientRepository.save(any(Ingredient.class))).thenAnswer(returnsFirstArg());
    when(converter.toDTO(ingredient)).thenReturn(ingredientDTO);

    ingredientService.update(ingredientDTO, INGREDIENT_ID);

    verify(ingredient).setNotes(ingredientDTO.getNotes());
    verify(ingredient).setName(ingredientDTO.getName());
  }

  @Test
  public void shouldReturnEmptyList_findAll() {
    when(ingredientRepository.findAll()).thenReturn(Collections.emptyList());
    var result = ingredientService.findAll();
    assertThat(result).hasSize(0);
  }

  @Test
  public void shouldReturnListOfIngredientDTO_findBySupplierID() {
    Ingredient ingredient = new Ingredient(INGREDIENT_NAME);
    IngredientDTO ingredientDTO = new IngredientDTO();
    ingredientDTO.setName(INGREDIENT_NAME);
    when(ingredientRepository.findBySupplierId(1L)).thenReturn(List.of(ingredient));
    when(converter.toDTO(ingredient)).thenReturn(ingredientDTO);

    List<IngredientDTO> result = ingredientService.findBySupplierId(1L);
    assertThat(result).hasSize(1).containsOnly(ingredientDTO);
  }

}