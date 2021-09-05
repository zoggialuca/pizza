package com.pizza.pizza.converter;

public interface EntityDTOBidirectionalConverter<E, D> extends EntityToDTOConverter<E, D>, DTOToEntityConverter<D, E> {

}
