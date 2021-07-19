package com.pizza.pizza;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.pizza.pizza.repository.PizzaRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PizzaApplicationTests {

	@Autowired private PizzaRepository pizzaRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void atLeastOnePizza(){
		assertEquals(true, pizzaRepository.count() > 1);
	}

}
