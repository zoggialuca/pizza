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
import com.pizza.pizza.service.PizzaRepositoryService;

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
			, PizzaRepositoryService pizzaRepositoryService
		){
		return args -> {
			var unitOfMeasures = Arrays.asList("pieces", "g");
			try {
				unitOfMeasures.stream()
					.map(unitOfMeasure -> new UnitOfMeasure(unitOfMeasure))
					.forEach(unitOfMeasure -> unitOfMeasureRepository.save(unitOfMeasure));
				logger.info("unit_of_measure populated");
			} catch (Exception e) {
				logger.info("Exception while populating unit_of_measure");
			}

			
			var ingredients = Arrays.asList("Mozzarella", "Pomodoro", "Aglio", "Prosciutto crudo");
			try {
				ingredients.stream()
					.map(ingredient -> new Ingredient(ingredient))
					.forEach(ingredient -> ingredientRepository.save(ingredient));
				logger.info("ingredient populated");
			} catch (Exception e) {
				logger.info("Exception while populating ingredient");
			}

			try {
				var pizzas = new ArrayList<Pizza>();
				pizzas.add(new Pizza("Margherita", true));
				pizzas.add(new Pizza("Marinara", true));
				pizzas.add(new Pizza("Prosciutto"));
				pizzas.stream().forEach(pizza -> {
					pizzaRepository.save(pizza);
	
					var unitOfMeasure = unitOfMeasureRepository.findByName(unitOfMeasures.get(0)).get();
					var quantity = Double.valueOf(new Random().nextInt(10) + 1);
	
					var pizzaIngredients = new HashSet<PizzaIngredient>();
	
					ingredients.stream()
						.limit(2)
						.map(ingredient -> ingredientRepository.findByName(ingredient).get())
						.forEach(ingredient -> {
							var pizzaIngredient = new PizzaIngredient(pizza, ingredient);
							pizzaIngredient.setQuantity(quantity);
							pizzaIngredient.setUnitOfMeasure(unitOfMeasure);
							pizzaIngredients.add(pizzaIngredient);
							pizzaIngredientRepository.save(pizzaIngredient);
						});
					pizza.setPizzaIngredients(pizzaIngredients);
				});
				logger.info("pizza populated");
			} catch (Exception e) {
				logger.info("Exception while populating pizza");
			}

			var additionalPizzas = new ArrayList<Pizza>();
			try {
				additionalPizzas.add(new Pizza("A", true));
				additionalPizzas.add(new Pizza("B", true));
				pizzaRepositoryService.bulkInsertTransactionNoAnnotation(additionalPizzas);
				logger.info("additionalPizzas added");
			} catch (Exception e) {
				logger.info("Exception while populating pizza with additional pizzas");
			}

			additionalPizzas.clear();
			additionalPizzas.add(new Pizza("C", true));
			additionalPizzas.add(new Pizza("A", true));

			try {
				pizzaRepositoryService.bulkInsertTransactionNoAnnotation(additionalPizzas);
				logger.info("NO ANNOTATION additionalPizzas added");
			} catch (Exception e) {
				logger.error("NO ANNOTATION additionalPizzas error!!!");
			}
			System.out.println("NO ANNOTATION");
			pizzaRepositoryService.findAll().stream().map(pizza -> pizza.getName()).forEach(System.out::println);
			
			try {
				pizzaRepositoryService.bulkInsertTransactionAnnotation(additionalPizzas);
				logger.info("ANNOTATION additionalPizzas added");
			} catch (Exception e) {
				logger.error("ANNOTATION additionalPizzas error!!!");
			}
			System.out.println("ANNOTATION");
			pizzaRepositoryService.findAll().stream().map(pizza -> pizza.getName()).forEach(System.out::println);
			
			try {
				pizzaRepositoryService.bulkInsertTransactionDummy(additionalPizzas);
				logger.info("DUMMY additionalPizzas added");
			} catch (Exception e) {
				logger.error("DUMMY additionalPizzas error!!!");
			}
			System.out.println("DUMMY");
			pizzaRepositoryService.findAll().stream().map(pizza -> pizza.getName()).forEach(System.out::println);
			
			try {
				pizzaRepositoryService.bulkInsertNoTransaction(additionalPizzas);
				logger.info("NO TRANSACTION additionalPizzas added");
			} catch (Exception e) {
				logger.error("NO TRANSACTION additionalPizzas error!!!");
			}
			System.out.println("NO TRANSACTION");
			pizzaRepositoryService.findAll().stream().map(pizza -> pizza.getName()).forEach(System.out::println);

			logger.info("Database populated");
		};
	}
}