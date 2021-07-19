package com.pizza.pizza;

import com.pizza.pizza.entity.PizzaRepository;

import java.util.Arrays;

import com.pizza.pizza.entity.Pizza;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication(exclude = {ErrorMvcAutoConfiguration.class})
public class PizzaApplication {

	private static final Logger logger = LoggerFactory.getLogger(PizzaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PizzaApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(PizzaRepository pizzaRepository){
		return (args) -> {
			pizzaRepository.save(new Pizza("Margherita"));
			pizzaRepository.save(new Pizza("Marinara"));

			Arrays.asList("Margherita", "Capricciosa").forEach(name -> {
				var pizza = pizzaRepository.findByName(name);
				logger.info(String.format("Pizza %s exists? %s", name, pizza != null));
			});

		};
	}

}
