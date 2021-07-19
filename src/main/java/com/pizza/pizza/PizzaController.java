package com.pizza.pizza;

import java.util.List;

import com.pizza.pizza.entity.Pizza;
import com.pizza.pizza.entity.PizzaRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@CrossOrigin("*")
public class PizzaController {

	@Autowired private PizzaRepository pizzaRepository;

	@GetMapping("/pizzas")
	public List<Pizza> getPizzas() {
		return pizzaRepository.findAll();
	}
}
