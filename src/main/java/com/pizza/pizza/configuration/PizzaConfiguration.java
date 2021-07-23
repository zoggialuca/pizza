package com.pizza.pizza.configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

import com.pizza.pizza.model.Ingredient;
import com.pizza.pizza.model.Pizza;
import com.pizza.pizza.model.PizzaIngredient;
import com.pizza.pizza.model.UnitOfMeasure;
import com.pizza.pizza.repository.IngredientRepository;
import com.pizza.pizza.repository.PizzaIngredientRepository;
import com.pizza.pizza.repository.PizzaRepository;
import com.pizza.pizza.repository.UnitOfMeasureRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PizzaConfiguration {

	private static final Logger logger = LoggerFactory.getLogger(PizzaConfiguration.class);

	@Bean
	public CommandLineRunner initDatabase(PizzaRepository pizzaRepository
			, IngredientRepository ingredientRepository
			, PizzaIngredientRepository pizzaIngredientRepository
			, UnitOfMeasureRepository unitOfMeasureRepository
		){
		return args -> {
			var unitOfMeasures = Arrays.asList("pieces", "g");
			unitOfMeasures.stream()
				.map(unitOfMeasure -> new UnitOfMeasure(unitOfMeasure))
				.forEach(unitOfMeasure -> unitOfMeasureRepository.save(unitOfMeasure));
			logger.info("unit_of_measure populated");

			var ingredients = Arrays.asList("Mozzarella", "Pomodoro", "Aglio", "Prosciutto crudo");
			ingredients.stream()
				.map(ingredient -> new Ingredient(ingredient))
				.forEach(ingredient -> ingredientRepository.save(ingredient));
			logger.info("ingredient populated");

			var pizzas = new ArrayList<Pizza>();
			pizzas.add(new Pizza("Margherita", true));
			pizzas.add(new Pizza("Marinara", true));
			pizzas.add(new Pizza("Prosciutto"));
			//pizzas.stream().forEach(pizza -> pizzaRepository.save(pizza));
			pizzas.stream().forEach(pizza -> {
				pizzaRepository.save(pizza);

				var ingredient = ingredientRepository.findByName(ingredients.get(0)).get();
				var unitOfMeasure = unitOfMeasureRepository.findByName(unitOfMeasures.get(0)).get();
				var quantity = Double.valueOf(new Random().nextInt(10) + 1);

				var pizzaIngredient = new PizzaIngredient(pizza, ingredient);
				pizzaIngredient.setQuantity(quantity);
				pizzaIngredient.setUnitOfMeasure(unitOfMeasure);

				var pizzaIngredients = new HashSet<PizzaIngredient>();
				pizzaIngredients.add(pizzaIngredient);
				pizza.setPizzaIngredients(pizzaIngredients);

				pizzaIngredientRepository.save(pizzaIngredient);
			});
			logger.info("pizza populated");

			logger.info("Database populated");
		};
	}
}