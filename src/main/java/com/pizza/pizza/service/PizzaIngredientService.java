package com.pizza.pizza.service;

import com.pizza.pizza.converter.DTOToEntityConverter;
import com.pizza.pizza.converter.EntityToDTOConverter;
import com.pizza.pizza.dto.PizzaIngredientRequestDTO;
import com.pizza.pizza.dto.PizzaIngredientResponseDTO;
import com.pizza.pizza.exception.IngredientNotFoundException;
import com.pizza.pizza.exception.PizzaIngredientAlreadyExistsException;
import com.pizza.pizza.exception.PizzaIngredientNotFoundException;
import com.pizza.pizza.exception.PizzaNotFoundException;
import com.pizza.pizza.exception.UnitOfMeasureNotFoundException;
import com.pizza.pizza.model.PizzaIngredient;
import com.pizza.pizza.repository.IngredientRepository;
import com.pizza.pizza.repository.PizzaIngredientRepository;
import com.pizza.pizza.repository.PizzaRepository;
import com.pizza.pizza.repository.UnitOfMeasureRepository;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PizzaIngredientService {

  private final DTOToEntityConverter<PizzaIngredientRequestDTO, PizzaIngredient> requestDTOToEntityConverter;
  private final EntityToDTOConverter<PizzaIngredient, PizzaIngredientResponseDTO> entityToResponseDTOConverter;
  private final PizzaIngredientRepository pizzaIngredientRepository;
  private final PizzaRepository pizzaRepository;
  private final IngredientRepository ingredientRepository;
  private final UnitOfMeasureRepository unitOfMeasureRepository;

  public PizzaIngredientResponseDTO findById(Long id) {
    return pizzaIngredientRepository.findById(id)
                                    .map(entityToResponseDTOConverter::toDTO)
                                    .orElseThrow(() -> new IngredientNotFoundException(id));
  }

  public PizzaIngredientResponseDTO create(PizzaIngredientRequestDTO pizzaIngredientRequestDTO) {
    var ingredient = requestDTOToEntityConverter.toOptionalEntity(pizzaIngredientRequestDTO);
    return ingredient.map(pizzaIngredientRepository::save)
                     .map(entityToResponseDTOConverter::toDTO)
                     .orElse(null);
  }

  @Transactional
  public PizzaIngredientResponseDTO update(PizzaIngredientRequestDTO pizzaIngredientRequestDTO, Long id) {
    var pizzaId = pizzaIngredientRequestDTO.getPizzaId();
    var pizza = pizzaRepository.findById(pizzaId)
                               .orElseThrow(() -> new PizzaNotFoundException(pizzaId));

    var ingredientId = pizzaIngredientRequestDTO.getIngredientId();
    var ingredient = ingredientRepository.findById(ingredientId)
                                         .orElseThrow(() -> new IngredientNotFoundException(ingredientId));

    var pizzaIngredient = pizzaIngredientRepository.findByPizzaIdAndIngredientId(pizzaId, ingredientId)
                                                   .orElseGet(() -> pizzaIngredientRepository.findById(pizzaIngredientRequestDTO.getId())
                                                                                             .orElseThrow(() -> new PizzaIngredientNotFoundException(id))
                                                       //no (pizza, ingredient) couple and no record for the current id
                                                   );
    if (!pizzaIngredient.getId().equals(id)) {
      //we have an error if the couple already exists and is linked to another record
      throw new PizzaIngredientAlreadyExistsException(pizzaId, ingredientId);
    }

    if (!(pizzaIngredient.getPizza().getId().equals(pizza.getId())
        && pizzaIngredient.getIngredient().getId().equals(ingredient.getId()))) {
      //at least, one key field has been changed. thus, we delete the existing record and we create a new one
      pizzaIngredientRepository.delete(pizzaIngredient);
      pizzaIngredient = new PizzaIngredient(pizza, ingredient);
    }

    var unitOfMeasureID = pizzaIngredientRequestDTO.getUnitOfMeasureId();
    var unitOfMeasure = unitOfMeasureRepository.findById(unitOfMeasureID)
                                               .orElseThrow(() -> new UnitOfMeasureNotFoundException(unitOfMeasureID));

    pizzaIngredient.setQuantity(pizzaIngredientRequestDTO.getQuantity());
    pizzaIngredient.setUnitOfMeasure(unitOfMeasure);
    return entityToResponseDTOConverter.toDTO(pizzaIngredientRepository.save(pizzaIngredient));
  }

  public void delete(Long id) {
    pizzaIngredientRepository.deleteById(id);
  }

  public List<PizzaIngredientResponseDTO> findByPizzaId(Long pizzaId) {
    return pizzaIngredientRepository.findByPizzaId(pizzaId).stream()
                                    .map(entityToResponseDTOConverter::toDTO)
                                    .collect(Collectors.toList());
  }
}
