package com.pizza.pizza.service;

import java.util.List;
import java.util.Optional;

import com.pizza.pizza.model.Pizza;
import com.pizza.pizza.repository.PizzaRepository;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

@Service
public class PizzaRepositoryService extends RepositoryService<Pizza, Long>{

	private static final Logger logger = LoggerFactory.getLogger(PizzaRepositoryService.class);
    
    protected PizzaRepository pizzaRepository;

    public PizzaRepositoryService(PizzaRepository pizzaRepository){
        super(pizzaRepository);
        this.pizzaRepository = pizzaRepository;
    }

    public Optional<Pizza> findByName(String name){
        return pizzaRepository.findByName(name);
    }

    public List<Pizza> findByIsVegetarian(Optional<Boolean> isVegetarian){
        return pizzaRepository.findByIsVegetarian(isVegetarian);
    }

    public boolean bulkInsertTransactionNoAnnotation(List<Pizza> pizzas){
        pizzaRepository.saveAll(pizzas);
        return true;
    }

    @Transactional
    public boolean bulkInsertTransactionAnnotation(List<Pizza> pizzas){
        pizzaRepository.saveAll(pizzas);
        return true;
    }

    @Transactional
    public boolean bulkInsertTransactionDummy(List<Pizza> pizzas){
        pizzas.forEach(pizza -> pizzaRepository.save(pizza));
        return true;
    }

    public boolean bulkInsertNoTransaction(List<Pizza> pizzas){
        pizzas.forEach(pizza -> {
            try{
                pizzaRepository.save(pizza);
            }
            catch (Exception e){
                logger.info(e.getMessage());
            }
        });
        return true;
    }
}
