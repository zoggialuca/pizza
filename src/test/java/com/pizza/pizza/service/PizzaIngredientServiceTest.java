package com.pizza.pizza.service;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.pizza.pizza.converter.DTOToEntityConverter;
import com.pizza.pizza.converter.EntityToDTOConverter;
import com.pizza.pizza.dto.PizzaIngredientRequestDTO;
import com.pizza.pizza.dto.PizzaIngredientResponseDTO;
import com.pizza.pizza.exception.IngredientNotFoundException;
import com.pizza.pizza.exception.PizzaIngredientAlreadyExistsException;
import com.pizza.pizza.exception.PizzaIngredientNotFoundException;
import com.pizza.pizza.exception.PizzaNotFoundException;
import com.pizza.pizza.exception.UnitOfMeasureNotFoundException;
import com.pizza.pizza.model.Ingredient;
import com.pizza.pizza.model.Pizza;
import com.pizza.pizza.model.PizzaIngredient;
import com.pizza.pizza.model.UnitOfMeasure;
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

  private final static Long PIZZA_ID = 1L;
  private final static Long INGREDIENT_ID = 1L;
  private final static Long PIZZA_INGREDIENT_ID = 1L;
  private static final Long UNIT_OF_MEASURE_ID = 1L;
  private final static String PIZZA_NAME = "Margarita";
  private final static String INGREDIENT_NAME = "Salami";
  private static final Pizza PIZZA;
  private static final Ingredient INGREDIENT;
  private static final String UNIT_OF_MEASURE_NAME = "GRAM";
  private static final UnitOfMeasure UNIT_OF_MEASURE;


  static {
    PIZZA = new Pizza(PIZZA_NAME);
    PIZZA.setId(PIZZA_ID);

    INGREDIENT = new Ingredient(INGREDIENT_NAME);
    INGREDIENT.setId(INGREDIENT_ID);

    UNIT_OF_MEASURE = new UnitOfMeasure(UNIT_OF_MEASURE_NAME);
    UNIT_OF_MEASURE.setId(UNIT_OF_MEASURE_ID);
  }

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

    var testRequest = new PizzaIngredientRequestDTO();
    testRequest.setPizzaId(PIZZA_ID);

    when(pizzaRepository.findById(PIZZA_ID)).thenReturn(Optional.empty());

    assertThatExceptionOfType(PizzaNotFoundException.class).isThrownBy(() -> pizzaIngredientService.update(testRequest, PIZZA_INGREDIENT_ID));
  }

  @Test
  void shouldThrowAnExceptionBecauseOfMissingIngredient() {

    var testRequest = new PizzaIngredientRequestDTO();
    testRequest.setPizzaId(PIZZA_ID);
    testRequest.setIngredientId(INGREDIENT_ID);

    when(pizzaRepository.findById(PIZZA_ID)).thenReturn(Optional.of(PIZZA));
    when(ingredientRepository.findById(INGREDIENT_ID)).thenReturn(Optional.empty());

    assertThatExceptionOfType(IngredientNotFoundException.class).isThrownBy(() -> pizzaIngredientService.update(testRequest, PIZZA_INGREDIENT_ID));
  }

  @Test
  void shouldThrowPizzaIngredientNotFoundException() {

    var testRequest = new PizzaIngredientRequestDTO();
    testRequest.setPizzaId(PIZZA_ID);
    testRequest.setIngredientId(INGREDIENT_ID);
    testRequest.setId(PIZZA_INGREDIENT_ID);

    when(pizzaRepository.findById(PIZZA_ID)).thenReturn(Optional.of(PIZZA));
    when(ingredientRepository.findById(INGREDIENT_ID)).thenReturn(Optional.of(INGREDIENT));
    when(pizzaIngredientRepository.findByPizzaIdAndIngredientId(PIZZA_ID, INGREDIENT_ID)).thenReturn(Optional.empty());
    when(pizzaIngredientRepository.findById(PIZZA_INGREDIENT_ID)).thenReturn(Optional.empty());

    assertThatExceptionOfType(PizzaIngredientNotFoundException.class).isThrownBy(() -> pizzaIngredientService.update(testRequest, PIZZA_INGREDIENT_ID));
  }

  @Test
  void shouldThrowPizzaIngredientAlreadyExistsException() {

    var testRequest = new PizzaIngredientRequestDTO();
    testRequest.setPizzaId(PIZZA_ID);
    testRequest.setIngredientId(INGREDIENT_ID);
    testRequest.setId(PIZZA_INGREDIENT_ID);
    PizzaIngredient pizzaIngredient = new PizzaIngredient(PIZZA, INGREDIENT);
    pizzaIngredient.setId(2L);

    when(pizzaRepository.findById(PIZZA_ID)).thenReturn(Optional.of(PIZZA));
    when(ingredientRepository.findById(INGREDIENT_ID)).thenReturn(Optional.of(INGREDIENT));
    when(pizzaIngredientRepository.findByPizzaIdAndIngredientId(PIZZA_ID, INGREDIENT_ID)).thenReturn(Optional.of(pizzaIngredient));

    assertThatExceptionOfType(PizzaIngredientAlreadyExistsException.class).isThrownBy(() -> pizzaIngredientService.update(testRequest, PIZZA_INGREDIENT_ID));
  }

  @Test
  void shouldThrowUnitOfMeasureNotFoundException() {

    var testRequest = new PizzaIngredientRequestDTO();
    testRequest.setPizzaId(PIZZA_ID);
    testRequest.setIngredientId(INGREDIENT_ID);
    testRequest.setId(PIZZA_INGREDIENT_ID);
    testRequest.setUnitOfMeasureId(UNIT_OF_MEASURE_ID);
    PizzaIngredient pizzaIngredient = new PizzaIngredient(PIZZA, INGREDIENT);
    pizzaIngredient.setId(PIZZA_INGREDIENT_ID);

    when(pizzaRepository.findById(PIZZA_ID)).thenReturn(Optional.of(PIZZA));
    when(ingredientRepository.findById(INGREDIENT_ID)).thenReturn(Optional.of(INGREDIENT));
    when(pizzaIngredientRepository.findByPizzaIdAndIngredientId(PIZZA_ID, INGREDIENT_ID)).thenReturn(Optional.of(pizzaIngredient));
    when(unitOfMeasureRepository.findById(PIZZA_INGREDIENT_ID)).thenReturn(Optional.empty());

    assertThatExceptionOfType(UnitOfMeasureNotFoundException.class).isThrownBy(() -> pizzaIngredientService.update(testRequest, PIZZA_INGREDIENT_ID));
  }

  @Test
  void shouldDelete() {

    var testRequest = new PizzaIngredientRequestDTO();
    testRequest.setPizzaId(PIZZA_ID);
    testRequest.setIngredientId(INGREDIENT_ID);
    testRequest.setId(PIZZA_INGREDIENT_ID);
    testRequest.setUnitOfMeasureId(UNIT_OF_MEASURE_ID);
    Pizza pizza2 = new Pizza("testName");
    pizza2.setId(2L);
    Ingredient ingredient2 = new Ingredient("Salami");
    ingredient2.setId(2L);
    PizzaIngredient pizzaIngredient = new PizzaIngredient(pizza2, ingredient2);
    pizzaIngredient.setId(INGREDIENT_ID);

    when(pizzaRepository.findById(PIZZA_ID)).thenReturn(Optional.of(PIZZA));
    when(ingredientRepository.findById(INGREDIENT_ID)).thenReturn(Optional.of(INGREDIENT));
    when(pizzaIngredientRepository.findByPizzaIdAndIngredientId(PIZZA_ID, PIZZA_INGREDIENT_ID)).thenReturn(Optional.of(pizzaIngredient));
    when(unitOfMeasureRepository.findById(UNIT_OF_MEASURE_ID)).thenReturn(Optional.of(UNIT_OF_MEASURE));

    pizzaIngredientService.update(testRequest, PIZZA_INGREDIENT_ID);

    verify(pizzaIngredientRepository).delete(pizzaIngredient);
  }

}
