package com.pizza.pizza.service;

import com.pizza.pizza.converter.EntityDTOBidirectionalConverter;
import com.pizza.pizza.dto.PizzaDTO;
import com.pizza.pizza.exception.IngredientAlreadyExistsException;
import com.pizza.pizza.exception.IngredientNotFoundException;
import com.pizza.pizza.exception.PizzaNotFoundException;
import com.pizza.pizza.model.Pizza;
import com.pizza.pizza.repository.PizzaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PizzaService {
    private final EntityDTOBidirectionalConverter<Pizza, PizzaDTO> converter;
    private final PizzaRepository pizzaRepository;

    public List<PizzaDTO> findAll() {
        return pizzaRepository.findAll().stream().map(converter::toDTO)
                .collect(Collectors.toList());
    }

    public List<PizzaDTO> findAll(int pageNo, int pageSize) {
        return pizzaRepository.findAll(PageRequest.of(pageNo, pageSize)).stream().map(converter::toDTO)
                .collect(Collectors.toList());
    }

    public PizzaDTO findById(Long id) {
        return pizzaRepository.findById(id).map(converter::toDTO)
                .orElseThrow(() -> new PizzaNotFoundException(id));
    }

    public PizzaDTO findByName(String name) {
        return pizzaRepository.findByName(name).map(converter::toDTO)
                .orElseThrow(() -> new PizzaNotFoundException(name));
    }

    public List<PizzaDTO> findByIsVegetarian(boolean isVegetarian) {
        return pizzaRepository.findByIsVegetarian(isVegetarian).stream().map(converter::toDTO)
                .collect(Collectors.toList());
    }

    public PizzaDTO create(PizzaDTO pizzaDTO) {
        var pizza = converter.toOptionalEntity(pizzaDTO);
        return pizza.map(pizzaRepository::save).map(converter::toDTO).orElse(null);
    }

    public PizzaDTO update(PizzaDTO pizzaDTO, Long id) {
        if (pizzaRepository.findByName(pizzaDTO.getName()).isPresent()) {
            throw new IngredientAlreadyExistsException(pizzaDTO.getName());
        }
        return pizzaRepository.findById(id)
                .map(pizza -> {
                    pizza.setVegetarian(pizzaDTO.isVegetarian());
                    return pizzaRepository.save(pizza);
                })
                .map(converter::toDTO)
                .orElseThrow(() -> new IngredientNotFoundException(id));
    }

    public void delete(Long id) {
        pizzaRepository.deleteById(id);
    }
}
