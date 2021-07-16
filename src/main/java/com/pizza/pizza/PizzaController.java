package com.pizza.pizza;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class PizzaController {

	@GetMapping("/pizzas")
	public List<Pizza> getPizzas() {
        return Arrays.asList(new Pizza("Margherita"), new Pizza("Capricciosa"), new Pizza("Diavola"));
	}
}
