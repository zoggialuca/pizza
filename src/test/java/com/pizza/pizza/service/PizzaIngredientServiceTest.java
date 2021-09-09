package com.pizza.pizza.service;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

import com.pizza.pizza.converter.DTOToEntityConverter;
import com.pizza.pizza.converter.EntityToDTOConverter;
import com.pizza.pizza.dto.PizzaIngredientRequestDTO;
import com.pizza.pizza.dto.PizzaIngredientResponseDTO;
import com.pizza.pizza.exception.IngredientNotFoundException;
import com.pizza.pizza.exception.PizzaNotFoundException;
import com.pizza.pizza.model.Pizza;
import com.pizza.pizza.model.PizzaIngredient;
import com.pizza.pizza.repository.IngredientRepository;
import com.pizza.pizza.repository.PizzaIngredientRepository;
import com.pizza.pizza.repository.PizzaRepository;
import com.pizza.pizza.repository.UnitOfMeasureRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PizzaIngredientServiceTest {

  @Mock
  private PizzaRepository pizzaRepository;

  @Mock
  private IngredientRepository ingredientRepository;

  @Mock
  private PizzaIngredientRepository pizzaIngredientRepository;

  @Mock
  private UnitOfMeasureRepository unitOfMeasureRepository;

  @Mock
  private DTOToEntityConverter<PizzaIngredientRequestDTO, PizzaIngredient> requestDTOToEntityConverter;

  @Mock
  private EntityToDTOConverter<PizzaIngredient, PizzaIngredientResponseDTO> entityToResponseDTOConverter;

  private PizzaIngredientService pizzaIngredientService;

  @BeforeEach
  void setup() {
    pizzaIngredientService = new PizzaIngredientService(
        requestDTOToEntityConverter,
        entityToResponseDTOConverter,
        pizzaIngredientRepository, pizzaRepository,
        ingredientRepository,
        unitOfMeasureRepository);
  }

  @Test
  void shouldThrowAnExceptionBecauseOfMissingPizza() {
    var pizzaId = 1L;
    var testRequest = PizzaIngredientRequestDTO.builder()
                                               .pizzaId(pizzaId)
                                               .build();
    when(pizzaRepository.findById(pizzaId)).thenReturn(Optional.empty());
    assertThatExceptionOfType(PizzaNotFoundException.class).isThrownBy(() -> pizzaIngredientService.update(testRequest, 1L));
  }

  @Test
  void shouldThrowAnExceptionBecauseOfMissingIngredient() {
    var pizzaId = 1L;
    var ingredientId = 1L;
    var testRequest = PizzaIngredientRequestDTO.builder()
                                               .pizzaId(pizzaId)
                                               .ingredientId(ingredientId)
                                               .build();
    when(pizzaRepository.findById(pizzaId)).thenReturn(Optional.of(Pizza.builder().name("testName").build()));
    when(ingredientRepository.findById(ingredientId)).thenReturn(Optional.empty());
    assertThatExceptionOfType(IngredientNotFoundException.class).isThrownBy(() -> pizzaIngredientService.update(testRequest, 1L));
  }
}
