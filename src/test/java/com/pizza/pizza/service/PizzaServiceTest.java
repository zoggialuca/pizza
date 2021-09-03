package com.pizza.pizza.service;

import com.pizza.pizza.converter.EntityDTOBidirectionalConverter;
import com.pizza.pizza.dto.PizzaDTO;
import com.pizza.pizza.exception.PizzaAlreadyExistsException;
import com.pizza.pizza.exception.PizzaNotFoundException;
import com.pizza.pizza.model.Pizza;
import com.pizza.pizza.repository.PizzaRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PizzaServiceTest {

    private static final PizzaDTO PIZZA_DTO = new PizzaDTO();

    private static final Pizza PIZZA = new Pizza("margharita");

    private static final Long ID = 1L;

    static {
        PIZZA_DTO.setName("margharita");
    }

    @Mock
    private EntityDTOBidirectionalConverter<Pizza, PizzaDTO> converter;
    @Mock
    private PizzaRepository pizzaRepository;

    private PizzaService pizzaService;

    @BeforeEach
    void setUp() {
        pizzaService = new PizzaService(converter, pizzaRepository);
    }

    @Test
    void shouldThrowAnExceptionBecausePizzaAlreadyExists() {
        when(pizzaRepository.findByName(PIZZA_DTO.getName())).thenReturn(Optional.of(new Pizza("margharita")));
        assertThatExceptionOfType(PizzaAlreadyExistsException.class).isThrownBy(() -> pizzaService.update(PIZZA_DTO, ID));
    }

    @Test
    void shouldThrowExceptionBecausePizzaNotFound() {
        when(pizzaRepository.findByName(PIZZA_DTO.getName())).thenReturn(Optional.empty());
        assertThatExceptionOfType(PizzaNotFoundException.class).isThrownBy(() -> pizzaService.update(PIZZA_DTO, ID));
    }

    @Test
    void shouldSuccessfullyUpdatePizza() {
        when(pizzaRepository.findByName(PIZZA_DTO.getName())).thenReturn(Optional.empty());
        when(pizzaRepository.findById(ID)).thenReturn(Optional.of(PIZZA));
        when(pizzaRepository.save(PIZZA)).thenReturn(PIZZA);
        when(converter.toDTO(PIZZA)).thenReturn(PIZZA_DTO);
        pizzaService.update(PIZZA_DTO, ID);
        verify(pizzaRepository).save(PIZZA);
    }

    @Test
    void shouldReturnPizzaListFromThePage() {
        var pizzaList = List.of(PIZZA);
        var pizzaDTOList = List.of(PIZZA_DTO);
        var pageNo = 1;
        int pageSize = 1;
        when(pizzaRepository.findAll(PageRequest.of(pageNo, pageSize))).thenReturn(new PageImpl<>(pizzaList));
        when(converter.toDTO(PIZZA)).thenReturn(PIZZA_DTO);
        var result = pizzaService.findAll(pageNo, pageSize);
        assertThat(result).isEqualTo(pizzaDTOList);
    }

}