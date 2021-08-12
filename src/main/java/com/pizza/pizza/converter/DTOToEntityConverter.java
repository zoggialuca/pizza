package com.pizza.pizza.converter;

import java.util.Optional;

public interface DTOToEntityConverter<D, E> {
    Optional<E> toOptionalEntity(D dto);
}
