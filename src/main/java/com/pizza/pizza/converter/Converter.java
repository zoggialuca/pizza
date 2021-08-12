package com.pizza.pizza.converter;

import java.util.Optional;

public interface Converter<E, D> {
    D toDTO(E entity);
    Optional<E> toOptionalEntity(D dto);
}
