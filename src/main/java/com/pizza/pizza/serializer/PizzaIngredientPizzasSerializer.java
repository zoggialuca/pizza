package com.pizza.pizza.serializer;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.pizza.pizza.model.PizzaIngredient;

public class PizzaIngredientPizzasSerializer extends StdSerializer<Set<PizzaIngredient>>{

    public PizzaIngredientPizzasSerializer(){
        this(null);
    }

    public PizzaIngredientPizzasSerializer(Class<Set<PizzaIngredient>> pizzaIngredients){
        super(pizzaIngredients);
    }

    @Override
    public void serialize(Set<PizzaIngredient> pizzaIngredients, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        var pizzaIngredientsList = pizzaIngredients.stream().map(pizzaIngredient -> pizzaIngredient.getPizza().getName()).collect(Collectors.toList());
        jsonGenerator.writeObject(pizzaIngredientsList);
    }
}
