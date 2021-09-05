package com.pizza.pizza.converter;

public interface EntityToDTOConverter<E, D> {

  D toDTO(E entity);
}
