package com.pizza.pizza.serializer;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.pizza.pizza.model.PizzaIngredient;

public class PizzaIngredientIngredientsSerializer extends StdSerializer<Set<PizzaIngredient>>{

    public PizzaIngredientIngredientsSerializer(){
        this(null);
    }

    public PizzaIngredientIngredientsSerializer(Class<Set<PizzaIngredient>> pizzaIngredients){
        super(pizzaIngredients);
    }

    @Override
    public void serialize(Set<PizzaIngredient> pizzaIngredients, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        var pizzaIngredientsList = pizzaIngredients.stream().map(pizzaIngredient -> pizzaIngredient.getIngredient().getName()).collect(Collectors.toList());
        jsonGenerator.writeObject(pizzaIngredientsList);
    }
}
