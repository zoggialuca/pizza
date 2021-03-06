package com.pizza.pizza;

import com.pizza.pizza.repository.IngredientRepository;
import com.pizza.pizza.repository.PizzaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication(exclude = {ErrorMvcAutoConfiguration.class})
public class PizzaApplication {

    private static final Logger logger = LoggerFactory.getLogger(PizzaApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(PizzaApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(PizzaRepository pizzaRepository, IngredientRepository ingredientRepository) {
        return args -> {
            Arrays.asList("Margherita", "Capricciosa", "Something").forEach(name -> {
                var pizza = pizzaRepository.findByName(name);
                logger.info(String.format("Pizza %s exists? %s", name, pizza.isPresent()));
            });

            Arrays.asList("Pomodoro", "Carciofo").forEach(name -> {
                var ingredient = ingredientRepository.findByName(name);
                logger.info(String.format("Ingrediente %s exists? %s", name, ingredient.isPresent()));
            });
        };
    }

}
