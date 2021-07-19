package com.pizza.pizza.configuration;

import java.util.Arrays;

import com.pizza.pizza.model.Pizza;
import com.pizza.pizza.repository.PizzaRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PizzaConfiguration {

	private static final Logger logger = LoggerFactory.getLogger(PizzaConfiguration.class);

	@Bean
	public CommandLineRunner initDatabase(PizzaRepository pizzaRepository){
		return args -> {
			var pizzaNameList = Arrays.asList("Margherita", "Marinara", "Prosciutto");
			pizzaNameList.stream().map(name -> new Pizza(name)).forEach(pizza -> pizzaRepository.save(pizza));

			logger.info("Database populated");
		};
	}
}