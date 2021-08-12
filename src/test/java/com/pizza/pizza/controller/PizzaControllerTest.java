package com.pizza.pizza.controller;

import com.pizza.pizza.dto.PizzaDTO;
import com.pizza.pizza.repository.PizzaRepository;
import io.restassured.common.mapper.TypeRef;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PizzaControllerTest extends ControllerTest {

    @Autowired
    private PizzaRepository repository;

    @Test
    void shouldReturnAllPizzas() {
        request().get("/pizzas").then().statusCode(200);
    }

    @ParameterizedTest
    @MethodSource("getPizzasToCreate")
    void shouldCreatePizza(PizzaDTO pizzaDTO) {
        var responseBody = request().body(pizzaDTO).post("/pizzas")
                .then().statusCode(201).extract().body().as(new TypeRef<EntityModel<PizzaDTO>>() {
        });
        assertThat(responseBody.getContent()).extracting("name").isEqualTo(pizzaDTO.getName());
        assertThat(repository.count()).isNotZero();
    }

    private static List<PizzaDTO> getPizzasToCreate() {
        var pizza1 = new PizzaDTO();
        pizza1.setName("Margherita");
        pizza1.setVegetarian(true);
        var pizza2 = new PizzaDTO();
        pizza2.setName("Capricciosa");
        pizza2.setVegetarian(false);
        return List.of(pizza1, pizza2);
    }
}
